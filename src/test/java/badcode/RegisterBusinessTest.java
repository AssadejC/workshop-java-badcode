package badcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {

    @Test
    public void case01(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            registerBusiness.register(null, new Speaker());
            fail();
        }catch (ArgumentNullException e){
            if(!e.getMessage().equals("First name is required.")){
                fail();
            }
        }
    }

    @Test
    public void case02(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker =  new Speaker();
            speaker.setFirstName("assadej");
            registerBusiness.register(null,speaker);
            fail();
        }catch (ArgumentNullException e){
            if(!e.getMessage().equals("Last name is required.")){
                fail();
            }
        }
    }

    @Test
    public void case03(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker =  new Speaker();
            speaker.setFirstName("assadej");
            speaker.setLastName("Chinawong");
            registerBusiness.register(null,speaker);
            fail();
        }catch (ArgumentNullException e){
            if(!e.getMessage().equals("Email is required.")){
                fail();
            }
        }
    }

    @Test
    public void case04(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker =  new Speaker();
            speaker.setFirstName("assadej");
            speaker.setLastName("Chinawong");
            speaker.setEmail("email@gmail.com");
            registerBusiness.register(null,speaker);
            fail();
        }catch (SaveSpeakerException e){
            if(!e.getMessage().equals("Can't save a speaker.")){
                fail();
            }
        }
    }

    @Test
    public void case05(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker =  new Speaker();
            speaker.setFirstName("assadej");
            speaker.setLastName("Chinawong");
            speaker.setEmail("email@hotmail.com");
            registerBusiness.register(null,speaker);
            fail();
        }catch (SpeakerDoesntMeetRequirementsException e){
            if(!e.getMessage().equals("Speaker doesn't meet our standard rules.")){
                fail();
            }
        }
    }

    @Test
    public void case06(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker =  new Speaker();
            speaker.setFirstName("assadej");
            speaker.setLastName("Chinawong");
            speaker.setEmail("email");
            registerBusiness.register(null,speaker);
            fail();
        }catch (DomainEmailInvalidException e){
        }
    }

    @Test
    public void case07(){
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker =  new Speaker();
        speaker.setFirstName("assadej");
        speaker.setLastName("Chinawong");
        speaker.setEmail("email@gmail.com");
        int speakerId = registerBusiness.register(new SpeakerRepository() {
            @Override
            public Integer saveSpeaker(Speaker speaker) {
                return 1;
            }
        }, speaker);
        assertEquals(1, speakerId);
    }
}