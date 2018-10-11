package mobdev.smartmenu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import model.Category;
import model.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment  extends Fragment implements AdapterView.OnItemClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
   // View myFragment;
    RecyclerView listProducts;
    FirebaseRecyclerAdapter<Food,ProductViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference products;
    LinearLayoutManager layoutManager;
    View view;
    String categoryId="";

    public static ProductsFragment newInstance(){
        ProductsFragment productsFragment=new ProductsFragment();
        return productsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        products=database.getReference("Food");
    }

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_products, container, false);
        TextView txt = (TextView) view.findViewById(R.id.categoryID);

        Bundle bundle = this.getArguments();

        txt.setText( "category : " + bundle.getString("categoryID", "0"));
        categoryId=bundle.getString("categoryID","0");
        listProducts=view.findViewById(R.id.listProduct);
        listProducts.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(container.getContext());
        listProducts.setLayoutManager(layoutManager);
        loadProducts();
        return view;

    }
    private void loadProducts() {
        adapter=new FirebaseRecyclerAdapter<Food, ProductViewHolder>(Food.class,R.layout.productview_item_layout,ProductViewHolder.class,products.orderByChild("categoryId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, final Food model, int position) {
                viewHolder.product_name.setText(model.getName());
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.product_image);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                      Toast.makeText(getActivity(),String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

        adapter.notifyDataSetChanged();
        listProducts.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
