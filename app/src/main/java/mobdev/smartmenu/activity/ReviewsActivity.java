package mobdev.smartmenu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobdev.smartmenu.ItemClickListener;
import mobdev.smartmenu.R;
import mobdev.smartmenu.viewholder.ReviewViewHolder;
import model.Review;

public class ReviewsActivity extends AppCompatActivity {

    public FirebaseRecyclerAdapter<Review, ReviewViewHolder> adapter;
    public FirebaseDatabase database;
    public DatabaseReference review;
    public android.support.v7.widget.RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_review);

        database = FirebaseDatabase.getInstance();
        //get review table from firebase
        review = database.getReference("Review");
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.restaurant_review_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        setupRecyclerView((android.support.v7.widget.RecyclerView) recyclerView);
    }

    // method for get all given reviews from firebase
    private void setupRecyclerView(android.support.v7.widget.RecyclerView recyclerView) {
        adapter = new FirebaseRecyclerAdapter<Review, ReviewViewHolder>(Review.class,
                R.layout.review_layout, ReviewViewHolder.class, review) {
            @Override
            protected void populateViewHolder(ReviewViewHolder viewHolder, Review model, int position) {

                viewHolder.reviewer.setText(model.getReviewer());
                viewHolder.reviewComment.setText(model.getComment());
                viewHolder.tableId.setText(model.getTableId());
                viewHolder.rating.setText(model.getRating());
                viewHolder.reviewDate.setText(model.getDate());
                viewHolder.reviewTime.setText(model.getTime());

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
