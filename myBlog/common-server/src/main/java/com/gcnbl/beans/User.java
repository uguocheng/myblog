package com.gcnbl.beans;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.upms.model.base.HyperModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "b_upms_user")
@Erupt(
        name = "用户",
        desc = "用户配置",
        dataProxy = User.class
        //linkTree = @LinkTree(field = "eruptOrg")
)
public class User extends HyperModel implements DataProxy<User> {

    @EruptField(
            views = @View(title = "姓名", sortable = true),
            edit = @Edit(title = "姓名", notNull = true, search = @Search(vague = true))
    )
    private String name;

    public User() {
    }

    public User(Long id) {
        this.setId(id);
    }
}




