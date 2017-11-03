package com.example.md.coordinatorlayoutfull;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PartClassifyFragment extends Fragment {
    @Bind(R.id.rv_part_classify)
    RecyclerView rvPartClassify;
    private CommonAdapter<String> adapter;
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part_classify, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        for (int i = 0; i < 15; i++) {
            datas.add("");
            ImageView imageView = new ImageView(getActivity());
            Bitmap bitmap = BitmapFactory.decodeFile("");
            imageView.setImageBitmap(bitmap);
        }
        rvPartClassify.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new CommonAdapter<String>(getActivity(), R.layout.item_part_classify, datas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        rvPartClassify.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
