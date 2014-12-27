package ngson.vn.app.zoominviewpager;

import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {
    //@InjectView(R.id.container) PagerContainer pagerContainer;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        viewPager.setPageMargin(30);
        viewPager.setOffscreenPageLimit(10);
        try {
            viewPager.setPageTransformer(true, (new TransformerItem(ZoomOutSlideTransformer.class)).clazz.newInstance());
        } catch (Exception e) {

        }
        setSizeViewPager();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    private void setSizeViewPager() {
        int w = ((3 * getWidthScreen()) / 4);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(w, FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        viewPager.setLayoutParams(params);
    }

    private int getWidthScreen() {
        Display display = getWindowManager().getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= 17) {
            Point p = new Point();
            display.getRealSize(p);

            return p.x;
        }

        return display.getWidth();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ContentFragment();
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

    private static final class TransformerItem {
        final String title;
        final Class<? extends ViewPager.PageTransformer> clazz;
        public TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
            this.clazz = clazz;
            title = clazz.getSimpleName();
        }
        @Override
        public String toString() {
            return title;
        }
    }
}
