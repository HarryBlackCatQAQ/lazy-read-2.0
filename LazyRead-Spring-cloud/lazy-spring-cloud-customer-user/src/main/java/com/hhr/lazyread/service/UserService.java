package com.hhr.lazyread.service;

import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    public Result loginUser(User user);

    public Result getUserCollectionBooks(User user);
}
