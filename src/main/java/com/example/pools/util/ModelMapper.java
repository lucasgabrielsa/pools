package com.example.pools.util;

import com.example.pools.model.Poll;
import com.example.pools.model.User;
import com.example.pools.payload.ChoiceResponse;
import com.example.pools.payload.PollResponse;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choicesVotesMap, User creator, Long userVote) {

        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDatetime(poll.getCreatedAt());
        pollResponse.setExpirationDatetime(poll.getExpirationDatetime());
        Instant now = Instant.now();
        pollResponse.setExpired(poll.getExpirationDatetime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
           ChoiceResponse choiceResponse = new ChoiceResponse();
           choiceResponse.setId(choice.getId());
           choiceResponse.setText(choice.getText());

           if(choicesVotesMap.containsKey(choice.getId())) {
               choiceResponse.setVoteCount(choicesVotesMap.get(choice.getId()));
           } else {
               choiceResponse.setVoteCount(0);
           }

           return choiceResponse;

        }).collect(Collectors.toList());

    }
}
