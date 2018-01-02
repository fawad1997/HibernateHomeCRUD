import model.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "mybean")
@SessionScoped
public class MyBean {
    private  UserBackingBean user = new UserBackingBean();
    private List<UserEntity> l;

    public void fillTaable(){

    }
    public String auth(){
        if(user.getPassword()!=null && user.getPassword()!=null){
            if(user.getPassword().equals("") && user.getPassword().equals("")) {
                //System.out.println(user.getEmail());
                //System.out.println(user.getPassword());
                Transaction t = null;
                Session s = Main.getSession();
                t = s.beginTransaction();
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(user.getEmail());
                userEntity.setPass(user.getPassword());
                s.save(userEntity);
                t.commit();
                s.close();
                return "welcome.xhtml";
            }
        }
        return null;
    }

    public boolean editUserById(int id) {
        System.out.println(id);
        Session session = Main.getSession();
        Transaction t = session.beginTransaction();
        UserEntity usertoedit = (UserEntity) session.load(UserEntity.class, id);
        usertoedit.setEmail("Esited");
        usertoedit.setPass("Edited");
        session.update(usertoedit);
        t.commit();
        session.close();
        System.out.println("End Tk Chala");
        return false;
    }
    public boolean deleteUserById(int id) {
        System.out.println(id);
        Session session = Main.getSession();
        Transaction t = session.beginTransaction();
        UserEntity usertoDel = (UserEntity) session.load(UserEntity.class, id);
        session.delete(usertoDel);
        t.commit();
        session.close();
        System.out.println("End Tk Chala");
            return false;
    }

    public void setL(List<UserEntity> l) {
        this.l = l;
    }

    public List<UserEntity> getL() {
        Transaction t = null;
        Session s = Main.getSession();
        t = s.beginTransaction();
        l = s.createQuery("from UserEntity").list();
        t.commit();
        s.close();
        return l;
    }

    public UserBackingBean getUser() {
        return user;
    }

    public void setUser(UserBackingBean user) {
        this.user = user;
    }
}
