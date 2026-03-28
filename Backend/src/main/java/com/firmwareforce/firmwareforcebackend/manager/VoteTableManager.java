package com.firmwareforce.firmwareforcebackend.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firmwareforce.firmwareforcebackend.repository.VoteRepository;
import java.util.List;
import com.firmwareforce.firmwareforcebackend.vote;
import com.firmwareforce.firmwareforcebackend.voteType;

@Service
public class VoteTableManager{

    @Autowired
    private VoteRepository voteRepository;

    public List<vote> getAllVotes() 
    {
        return voteRepository.findAll();
    }

    public vote getVoteByUserIdReportId(Long userId, Long reportId)
    {
        List<vote> votes = voteRepository.findAll();

        for (vote Vote: votes)
        {
            if ((Vote.getUserId() == userId) && (Vote.getReportId() == reportId))
            {
                return Vote;
            }
        }

        return null;
    }

    public void createVote(Long userId, Long reportId, voteType VoteType)
    {
        vote Vote = new vote();
        Vote.setUserId(userId);
        Vote.setReportId(reportId);
        Vote.setVoteType(VoteType);

        voteRepository.save(Vote);
    }
    
    public void deleteVoteByUserIdReportId(Long userId, Long reportId)
    {
        vote Vote = getVoteByUserIdReportId(userId, reportId);
        voteRepository.delete(Vote);
    }
}
