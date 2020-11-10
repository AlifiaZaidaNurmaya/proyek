package org.proyek.parkirassistant.customScanPaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.proyek.parkirassistant.R;

import androidx.annotation.ColorRes;
import me.dm7.barcodescanner.core.ViewFinderView;

public class CustomViewFinderView extends ViewFinderView {

    private final Paint paint = new Paint();
    private final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    private int scannerAlpha = 0;
    private int center = 0;
    private boolean goingup = false;
    private int POINT_SIZE = 10;
    private long ANIMATION_DELAY = 80L;

    public CustomViewFinderView(Context context) {
        super(context);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        setSquareViewFinder(true);
        setBorderColor(Color.parseColor("#F0A500"));
        setLaserColor(Color.parseColor("#F0A500"));
        setLaserEnabled(true);
    }

    @Override
    public void drawLaser(Canvas canvas) {
        super.drawLaser(canvas);
        paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
        scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
        int middle = getFramingRect().height() / 2 + getFramingRect().top;
        middle += center;
        if (center < getFramingRect().top - mBorderLineLength - 10 && !goingup) {
            canvas.drawRect((float) (getFramingRect().left + 2), (float) (middle - 1),
                    (float) (getFramingRect().right - 1), (float) (middle + 2), mLaserPaint);
            center += 4;
        }
        if (center >= getFramingRect().top - mBorderLineLength - 10 && !goingup) goingup = true;
        if (center > -getFramingRect().top + mBorderLineLength + 10 && goingup) {
            canvas.drawRect(Float.intBitsToFloat((getFramingRect().left + 2)),
                    (float)(middle - 1), (getFramingRect().right - 1), (float)(middle + 2), mLaserPaint);
            center -= 4;
        }
        if (center <= -getFramingRect().top + mBorderLineLength + 10 && goingup) goingup = false;

        postInvalidateDelayed(ANIMATION_DELAY,
                getFramingRect().left - POINT_SIZE,
                getFramingRect().top - POINT_SIZE,
                getFramingRect().right + POINT_SIZE,
                getFramingRect().bottom + POINT_SIZE);
    }
}
