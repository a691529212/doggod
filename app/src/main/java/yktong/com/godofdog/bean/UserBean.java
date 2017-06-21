package yktong.com.godofdog.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by vampire on 2017/6/16.
 */

public class UserBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String userName;
    private String password;

}
