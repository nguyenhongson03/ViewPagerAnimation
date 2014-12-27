package ngson.vn.app.zoominviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ngson on 24/12/2014.
 */
public class ContentFragment extends Fragment {
    @InjectView(R.id.root) View root;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_content, container, false);

        ButterKnife.inject(this, root);

        return root;
    }

    public View getRoot() {
        return root;
    }
}
