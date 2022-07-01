package bo;

import bo.custom.impl.MakeRegistrationBOImpl;
import bo.custom.impl.PasswordBOImpl;
import bo.custom.impl.RoomBOImpl;
import bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            return new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        ROOM,STUDENT,RESERVATION,PASSWORD;
    }
    public SuperBO getBO(BOFactory.BOTypes types){
        switch (types){
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case RESERVATION:
                return new MakeRegistrationBOImpl();
            case PASSWORD:
                return new PasswordBOImpl();
            default:
                return null;
        }
    }
}
