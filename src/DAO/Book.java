package DAO;

public class Book {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String  bookname;
    private String  author;
    private String  chapterPath;
    private String  bookFile;
    public Book(){}
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setChapterPath(String chapterPath) {
		this.chapterPath = chapterPath;
	}
	public String getChapterPath() {
		return chapterPath;
	}
	public void setBookFile(String bookFile) {
		this.bookFile = bookFile;
	}
	public String getBookFile() {
		return bookFile;
	}
	
}
