package com.darkspacelab.gra2dzdotykiem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.HashMap;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {


    private GameThread gameThread;
    private HashMap<Integer, PostacChibi> postacie = new HashMap<Integer, PostacChibi>();
    private int chibiId = 1;
    private PostacChibi chibi;
    Bitmap chibiBoom;
    Bitmap chibiBitmap1;

    public GameSurface(Context context) {
        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);
    }

    public void update() {
        if (postacie.size() > 0) {
            for (Integer licznik : postacie.keySet()) {
                chibi = postacie.get(licznik);
                chibi.aktualizuj();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (postacie.size() > 0) {
            for (Integer licznik : postacie.keySet()) {
                chibi = postacie.get(licznik);
                chibi.rysuj(canvas);
            }
        }
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
        chibiBoom = BitmapFactory.decodeResource(this.getResources(), R.drawable.boom);
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int movingVectorY = 0;
            int movingVectorX = 0;
            float nacisk = motionEvent.getPressure();

            if (postacie.size() > 0) {
                for (Integer licznik : postacie.keySet()) {
                    chibi = postacie.get(licznik);

                    movingVectorX = x - chibi.getX();
                    movingVectorY = y - chibi.getY();

                    chibi.setmPoruszajacyWektor(movingVectorX, movingVectorY);

                    if (x >= chibi.getX() && x < chibi.getX() + chibi.mSzerokosc
                            && y >= chibi.getY() && y < chibi.getY() + chibi.mWysokosc) {
                        try {
                            chibi.setEksploduje();
                           // postacie.remove(chibi.id);
                        } catch (Exception e) {
                            System.out.println("Error: " + e);
                        }
                    }
                }
            }
            if (nacisk > 1.5) {
                postacie.put(chibiId, new PostacChibi(this, chibiBitmap1, chibiBoom, x, y, 0.2f, chibiId));
                chibiId++;
            }
            return true;
        }
        return false;
    }
}