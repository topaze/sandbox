package dummy.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "releves")
public class Releve {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    
    @Column(name = "date", nullable = false, length = 8)
    private String date;
    
    @Column(name = "index", nullable = false)
    private Integer index;

    public Releve() {	
    }
    
    public Releve(final String type,final String date,final Integer index) {
	this.type = type;
	this.date = date;
	this.index = index;
    }
    
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public Integer getIndex() {
        return index;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
