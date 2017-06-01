package com.darkspacelab.gra2dzdotykiem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {


    private GameThread gameThread;

    private PostacChibi chibi1, chibi2;

    public GameSurface(Context context)  {
        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);
    }

    public void update()  {
        this.chibi1.aktualizuj();
        this.chibi2.aktualizuj();
    }

    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);

        this.chibi1.rysuj(canvas);
        this.chibi2.rysuj(canvas);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
        Bitmap chibiBoom = BitmapFactory.decodeResource(this.getResources(), R.drawable.boom);
        this.chibi1 = new PostacChibi(this,chibiBitmap1,chibiBoom,100,50,0.1f);
        this.chibi2 = new PostacChibi(this,chibiBitmap1,chibiBoom,400,600,0.2f);
        this.gameThread = new GameThread(this,holder);
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
        while(retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getAction() == motionEvent.ACTION_DOWN) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();

            int movingVectorX = x - this.chibi1.getX();
            int movingVectorY = y - this.chibi1.getY();

            int movingVectorX2 =x-  this.chibi2.getX();
            int movingVectorY2 =y-  this.chibi2.getY();

            chibi1.setmPoruszajacyWektor(movingVectorX,movingVectorY);
            chibi2.setmPoruszajacyWektor(movingVectorX2,movingVectorY2);
            System.out.println("x: "+x+", y: "+y);
            return true;

        }
        return false;
    }

}