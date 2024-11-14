package com.ms.arturlogan.email.consumers;


import com.ms.arturlogan.email.entities.Email;
import com.ms.arturlogan.email.entities.dto.EmailRecordDto;
import com.ms.arturlogan.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto recordDto){
        var email = new Email();
        BeanUtils.copyProperties(recordDto, email);
        emailService.sendEmail(email);

    }
}
