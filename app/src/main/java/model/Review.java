package model;

//model class for review
public class Review {
    private String comment;
    private String rating;
    private String reviewer;
    private String tableId;

    public Review() {
    }

    public Review(String comment, String rating, String reviewer, String tableId) {
        this.comment = comment;
        this.rating = rating;
        this.reviewer = reviewer;
        this.tableId = tableId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
