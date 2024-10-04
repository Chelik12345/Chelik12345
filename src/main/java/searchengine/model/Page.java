package searchengine.model;

import javax.persistence.*;

@Entity
@Table(name = "page")
public class Page {
    private String url;
    private String title;
    private String content;

    // Конструктор
    public Page(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    // Геттеры
    public String getUrl() {
        return url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String path;

    @Column(columnDefinition = "INT")
    private int code;
    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

}
