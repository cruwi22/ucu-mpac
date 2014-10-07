package com.ucu.ucu_mpac;

public class Collection {
	
	private int id;
	private String accessno;
	private String title;
	private String author;
	private String publisher;
	private String edition;
	private String volume;
	private String pages;
	private String cyear;
	private String csection;
	private String copies;
	private String babarcode;
	private String completecn;
	private String format;

	public Collection() { }

	public Collection(int id, String accessno, String title, String author,
			String publisher, String edition, String volume, String pages,
			String cyear, String csection, String copies, String babarcode,
			String completecn, String format) {
		super();
		this.id = id;
		this.accessno = accessno;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.volume = volume;
		this.pages = pages;
		this.cyear = cyear;
		this.csection = csection;
		this.copies = copies;
		this.babarcode = babarcode;
		this.completecn = completecn;
		this.format = format;
	}
	
	public Collection(String accessno, String title, String author,
			String publisher, String edition, String volume, String pages,
			String cyear, String csection, String copies, String babarcode,
			String completecn, String format) {
		super();
		this.accessno = accessno;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.volume = volume;
		this.pages = pages;
		this.cyear = cyear;
		this.csection = csection;
		this.copies = copies;
		this.babarcode = babarcode;
		this.completecn = completecn;
		this.format = format;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccessno() {
		return accessno;
	}

	public void setAccessno(String accessno) {
		this.accessno = accessno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	public String getCsection() {
		return csection;
	}

	public void setCsection(String csection) {
		this.csection = csection;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getBabarcode() {
		return babarcode;
	}

	public void setBabarcode(String babarcode) {
		this.babarcode = babarcode;
	}

	public String getCompletecn() {
		return completecn;
	}

	public void setCompletecn(String completecn) {
		this.completecn = completecn;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
