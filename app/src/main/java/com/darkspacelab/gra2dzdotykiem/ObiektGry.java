package com.darkspacelab.gra2dzdotykiem;

import android.graphics.Bitmap;

public abstract class ObiektGry {

    protected Bitmap mObraz;
    protected Bitmap mBoom;

    protected static final int mBoomWysokosc = 64;
    protected static final int mBoomSzerokosc = 64;

    protected final int mLiczbaWierszy;
    protected final int mLiczbaKolumn;

    protected final int SZEROKOSC_GRAFIKI;
    protected final int WYSOKOSC_GRAFIKI;

    protected final int mSzerokosc;
    protected final int mWysokosc;

    protected int mX;
    protected int mY;

    public ObiektGry(Bitmap obraz,Bitmap boom, int liczbaWierszy, int liczbaKolumn, int x, int y)  {

        this.mBoom = boom;
        this.mObraz = obraz;
        this.mLiczbaWierszy = liczbaWierszy;
        this.mLiczbaKolumn = liczbaKolumn;


        this.mX = x;
        this.mY = y;

        this.SZEROKOSC_GRAFIKI = obraz.getWidth();
        this.WYSOKOSC_GRAFIKI = obraz.getHeight();

        this.mSzerokosc = this.SZEROKOSC_GRAFIKI / liczbaKolumn;
        this.mWysokosc = this.WYSOKOSC_GRAFIKI / liczbaWierszy;
    }


    protected Bitmap oddzielSkorke(int wiersz, int kolumna)  {
        // createBitmap(bitmap, mX, mY, mSzerokosc, mWysokosc).
        Bitmap skorka = Bitmap.createBitmap(mObraz, kolumna * mSzerokosc, wiersz * mWysokosc, mSzerokosc, mWysokosc);
        return skorka;
    }

    protected Bitmap eksplozja(int fazaEksplozji) {
        Bitmap skin;
        if (fazaEksplozji <= 5) {
            skin = Bitmap.createBitmap(mBoom, fazaEksplozji *mBoomSzerokosc, 0, mBoomSzerokosc, mBoomWysokosc);
        }
        else if(fazaEksplozji > 5 && fazaEksplozji <= 10) {
            skin = Bitmap.createBitmap(mBoom, fazaEksplozji-5 *mBoomSzerokosc, mBoomWysokosc, mSzerokosc, mWysokosc);
        }
        else if(fazaEksplozji > 10 && fazaEksplozji <= 15) {
            skin = Bitmap.createBitmap(mBoom, fazaEksplozji-10 *mBoomSzerokosc, 2*mBoomWysokosc, mSzerokosc, mWysokosc);
        }
        else if(fazaEksplozji > 15 && fazaEksplozji <= 20) {
            skin = Bitmap.createBitmap(mBoom, fazaEksplozji-15 *mBoomSzerokosc, 3*mBoomWysokosc, mSzerokosc, mWysokosc);
        }
        else if(fazaEksplozji > 20 && fazaEksplozji <= 25) {
            skin = Bitmap.createBitmap(mBoom, fazaEksplozji-20 *mBoomSzerokosc, 4*mBoomWysokosc, mSzerokosc, mWysokosc);
        }
        return skin;
    }

    public int getX()  {
        return this.mX;
    }

    public int getY()  {
        return this.mY;
    }


    public int getWysokosc() {
        return mWysokosc;
    }

    public int getSzerokosc() {
        return mSzerokosc;
    }

}