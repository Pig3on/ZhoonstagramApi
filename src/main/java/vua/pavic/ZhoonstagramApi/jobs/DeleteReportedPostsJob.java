package vua.pavic.ZhoonstagramApi.jobs;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.services.PostService;

import java.util.List;

public class DeleteReportedPostsJob extends QuartzJobBean {

    @Autowired
    private PostService service;

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    @Override
    protected void executeInternal(org.quartz.JobExecutionContext context) throws JobExecutionException {
        List<Post> postsToDelete = service.getAllReportedPosts();
        service.deleteMany(postsToDelete);
    }
}
