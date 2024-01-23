package com.ayoub.myproject.topic;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Role;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import  org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@WebMvcTest(TopicController.class)
@ExtendWith(InvalidTopicIdExceptionExtension.class)
class TopicControllerTest {

@Autowired
MockMvc mockMvc;

@MockBean
    TopicService topicService;
    Topic Record_1 = new Topic("1" , "mohamed" , "mohamed's Description");
    Topic Record_2 = new Topic("2" , "Ali" , "Ali's Description");
    Topic Record_3 = new Topic("3" , "John" , "John's Description");

    Topic Record_4 = new Topic("-1" , "Amir" , "Amir's Description");

    @Test
    void getTopics_Success() throws Exception {
         List<Topic> topics = new ArrayList<>(Arrays.asList(Record_1 , Record_2 , Record_3));
        Mockito.when(topicService.GetAlTopics()).thenReturn(topics);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/topics")
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("John")));

    }

    @Test
    void getTopicbyId_Success() throws Exception{
        Mockito.when(topicService.GetTopic(Record_1.getId())).thenReturn(java.util.Optional.of(Record_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/topics/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("mohamed")));
    }

    @Test
    void getTopicbyId_InavlidTopicIdException() throws Exception{
        Mockito.when(topicService.GetTopic("-1")).thenThrow(new InavlidTopicIdException("Cannot Have Negative Id!"));

        InavlidTopicIdException exception= assertThrows(InavlidTopicIdException.class , () -> {
                topicService.GetTopic("-1");
        });
        assertEquals("Cannot Have Negative Id!" , exception.getMessage());

    }


    //I have here an issue, can't implement the behaviour of ExtendWith to use it in this case
    @Test
    void getTopicbyId_InavlidTopicIdExceptionUsingExtendWith(){
        assertThrows(InavlidTopicIdException.class, () -> {
            topicService.GetTopic("-1");
        }, "Expected exception message");


    }



}