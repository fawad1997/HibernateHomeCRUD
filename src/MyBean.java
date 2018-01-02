import model.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "mybean")
@SessionScoped
public class MyBean {
    private  UserBackingBean user = new UserBackingBean();
    private List<UserEntity> l;

    public void fillTaable(){
        Transaction t = null;
        Session s = Main.getSession();
        t = s.beginTransaction();
        l = s.createQuery("from UserEntity").list();
        t.commit();
        s.close();
        for (int i=0;i<l.size();i++){
            System.out.println(l.get(i).getEmail());
        }
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

    public List<UserEntity> getL() {
        return l;
    }

    public void setL(List<UserEntity> l) {
        this.l = l;
    }

    public UserBackingBean getUser() {
        return user;
    }

    public void setUser(UserBackingBean user) {
        this.user = user;
    }
}
