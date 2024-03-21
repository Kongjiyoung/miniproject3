package com.many.miniproject1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserJPARepository userJPARepository;

}
