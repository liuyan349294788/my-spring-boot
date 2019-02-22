package com.clockbone.esrepository;

import com.clockbone.esentity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> findByAgeBetween(double age1, double age2);

}
