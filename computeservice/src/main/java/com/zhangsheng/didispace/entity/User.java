package com.zhangsheng.didispace.entity;

/**
 *
 * @author zhangsheng
 * @version 1.0.0
 * @blog 
 *
 */

/*注意的是，我的数据库中的字段名称实际是带下划线的。比如parent_org，对于这样的字段，springboot中约定你必须在
实体类中设置为 private String parentOrg；就是第一个小写。第二个首字母大写。这样就自动匹配上了你的数据库字段。否则你还需要在实体类中的每个字段上
配置@Column（..），显然没必要。

导致bean无法注入，加一下就行了。和表对应就写@Entity，不对应就写@Component。
*/
public class User {

    private Long id;
    private String name;
    private Integer age;

    public User(String string, int i) {
		// TODO Auto-generated constructor stub
	}
    public User() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
