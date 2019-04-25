package com.clockbone.esrepository;

import com.clockbone.esentity.Acticle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ActicleRepository extends ElasticsearchRepository<Acticle, Long> {


}
