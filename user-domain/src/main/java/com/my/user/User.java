package com.my.user;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.my.user.attributes.EMailAddress;
import com.my.user.attributes.Password;
import com.my.user.attributes.Username;
import com.my.user.converter.dynamo.EmailAddressConverter;
import com.my.user.converter.dynamo.PasswordConverter;
import com.my.user.converter.dynamo.UsernameConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@DynamoDBTable(tableName = "user-v1" )
@NoArgsConstructor
public class User {

    @Id
    @DynamoDBHashKey
    private String id;

    @DynamoDBTypeConverted(converter = UsernameConverter.class)
    private Username username;

    @DynamoDBTypeConverted(converter = PasswordConverter.class)
    private Password password;

    @DynamoDBTypeConverted(converter = EmailAddressConverter.class)
    private EMailAddress email;
    private String fullname;
    private String token;

    User(UserBuilder.Builder builder) {
        this.username = builder.getUsername();
        this.password = builder.getPassword();
        this.email = builder.getEmail();
        this.fullname = builder.getFullname();
    }

}
