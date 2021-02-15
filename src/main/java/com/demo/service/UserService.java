package com.demo.service;

import com.demo.Authentication.UserRole;
import com.demo.dto.UserSignUpDto;
import com.demo.model.BlackList;
import com.demo.model.Offer;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    CityService cityService;
    @Autowired
    BlackListService blackListService;
    @Autowired
    OfferService offerService;
    @Autowired
    UserRoleService userRoleService;

    public HttpStatus signUp(UserSignUpDto userDto){
        User existingUser = findByEmail(userDto.getEmail());
        if(blackListService.findByEmail(userDto.getEmail())!=null)return HttpStatus.FORBIDDEN;
        if (existingUser!=null)return HttpStatus.BAD_REQUEST;
        if(userDto.getFirstName()!=null &&
                userDto.getLastName()!=null &&
                userDto.getDistrict()!=null&&
                userDto.getCity() != null &&
                userDto.getContactNumber()!=null &&
                userDto.getPassword()!= null ) {

                User user=new User();
                user.setFirstName(userDto.getFirstName());
                user.setLastName(user.getLastName());
                user.setDistrict(districtService.findDistrictByDistrictName(userDto.getDistrict()));
                user.setRoles(userDto.getRole().stream().map(x->userRoleService.findByName(x)).collect(Collectors.toList()));
                BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);
                String password =bCryptPasswordEncoder.encode(userDto.getPassword());
                user.setPassword(password);
                user.setContactNumber(userDto.getContactNumber());
                user.setEmail(userDto.getEmail());
                if (userRepository.save(user) != null) {
                    return HttpStatus.OK;
                }
        }return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus updateUser(long userId,UserSignUpDto updateUserDto){
        try{
            findById(userId);
        }catch (NullPointerException ex){
            return HttpStatus.NOT_FOUND;
        }
        User user = findById(userId);
        if(updateUserDto.getFirstName()!=null)user.setFirstName(updateUserDto.getFirstName());
        if(updateUserDto.getLastName()!=null)user.setLastName(updateUserDto.getLastName());
        if(updateUserDto.getPassword()!=null)user.setPassword(updateUserDto.getPassword());
        if(updateUserDto.getContactNumber()!=null)user.setContactNumber(updateUserDto.getContactNumber());
        if(updateUserDto.getDistrict()!=null)user.setDistrict(districtService.findDistrictByDistrictName(updateUserDto.getDistrict()));
        if(updateUserDto.getEmail()!=null)user.setEmail(updateUserDto.getEmail());
        userRepository.save(user);
        return HttpStatus.OK;
    }

    public User findById(long userId){
        return userRepository.findById(userId).orElseThrow(NullPointerException::new);
    }

    public User findByusername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){return  userRepository.findByEmail(email);}

    public void deleteUser(long userId){
        User user = findById(userId);
        userRepository.deleteFromUserReportedOffers(userId);
        for (Offer offer: user.getOffers()) {
            offerService.deleteOffer(offer.getId());
        }
        userRepository.delete(findById(userId));
    }

    public void banUser(long userId){
        blackListService.save(new BlackList(findById(userId).getEmail()));
        deleteUser(userId);
    }
}
