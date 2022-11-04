package umc.crudproject.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl {
    @Autowired
    private HomeRepository homeRepository;
}
