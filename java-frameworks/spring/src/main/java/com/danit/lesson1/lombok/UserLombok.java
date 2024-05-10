package com.danit.lesson1.lombok;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(makeFinal=false, level= AccessLevel.PRIVATE)
public class UserLombok {
    Long id;
    String name;
    int age;
    Long groupId;
    String login;
    String password;
}
