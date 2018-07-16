package com.david.demo.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.david.demo.user.UserDTO;
import com.david.demo.user.UserOrigin;

@Service
public class FacebookProvider  {

    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    BaseProvider baseProvider ;


    public String getFacebookUserData(Model model, UserDTO userDTO) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return REDIRECT_LOGIN;
        }
        model.addAttribute("user",populateUserDetailsFromFacebook(userDTO));
        return "hello";
    }

    protected UserDTO populateUserDetailsFromFacebook(UserDTO userDTO) {
        Facebook facebook = baseProvider.getFacebook();
        //String [] fields = { "id", "email",  "first_name", "last_name" };
        String [] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", "website", "work"};
        User userProfile = facebook.fetchObject("me", User.class, fields);
        userDTO.setFirstName(userProfile.getFirstName());
        userDTO.setLastName(userProfile.getLastName());
        userDTO.setUsername(userProfile.getName());
        userDTO.setGender(userProfile.getGender());
        userDTO.setEmail(userProfile.getEmail());
        userDTO.setUserOrigin(UserOrigin.FACEBOOK);
        System.out.println(userDTO);
        return userDTO;
        /*
        userForm.setImage(user.getCover().getSource());
        */
    }



}