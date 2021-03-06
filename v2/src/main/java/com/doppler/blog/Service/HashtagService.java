package com.doppler.blog.Service;

import com.doppler.blog.dao.HashtagDao;
import com.doppler.blog.models.Hashtag;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by doppler on 2016/5/30.
 */
@Service
public class HashtagService {

   @Resource
   private HashtagDao hashtagDao;

    private static final String CACHE_TAGS = "tags";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HashtagService.class);

    @CacheEvict(value = CACHE_TAGS, allEntries = true)
    public Hashtag findOrCreateByName(String name){
        Hashtag hashtag = hashtagDao.findTagByName(name);
//        if(hashtag == null || hashtag.getName().isEmpty()) {
//            hashtag = new Hashtag(name);
//            hashtag.setCreatedAt(DateFormatter.format(new Date()));
//            hashtagRepository.insert(hashtag);
//            logger.info(GlobalConstants.INSERTHASHTAG.value());
//        }
        return hashtag;
    }
    public Hashtag findByName(String tagName){
        return hashtagDao.findTagByName(tagName);
    }

    @Cacheable(value = CACHE_TAGS)
    public List<Hashtag> findAll(){
        logger.info("not caches,get all tags from db");
        return hashtagDao.findAll();
    }

    @CacheEvict(value = CACHE_TAGS, allEntries = true)
    public void deleteTag(String hashtagId) {
       // hashtagRepository.delete(hashtagId);
    }
}
