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
        Bitmap skin = null;
        if (fazaEksplozji <= 4) {
            skin = Bitmap.createBitmap(mBoom, fazaEksplozji *mBoomSzerokosc, 0, mBoomSzerokosc, mBoomWysokosc);
        }
        else if(fazaEksplozji > 4 && fazaEksplozji <= 9) {
            skin = Bitmap.createBitmap(mBoom, (fazaEksplozji-5) *mBoomSzerokosc, mBoomWysokosc, mBoomSzerokosc, mBoomWysokosc);
        }
        else if(fazaEksplozji > 9 && fazaEksplozji <= 14) {
            skin = Bitmap.createBitmap(mBoom, (fazaEksplozji-10) *mBoomSzerokosc, 2*mBoomWysokosc, mBoomSzerokosc, mBoomWysokosc);
        }
        else if(fazaEksplozji > 14 && fazaEksplozji <= 19) {
            skin = Bitmap.createBitmap(mBoom, (fazaEksplozji-15) *mBoomSzerokosc, 3*mBoomWysokosc, mBoomSzerokosc, mBoomWysokosc);
        }
        else if(fazaEksplozji > 19 && fazaEksplozji <= 24) {
            skin = Bitmap.createBitmap(mBoom, (fazaEksplozji-20) *mBoomSzerokosc, 4*mBoomWysokosc, mBoomSzerokosc, mBoomWysokosc);
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