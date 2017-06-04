package com.darkspacelab.gra2dzdotykiem;

import android.graphics.Bitmap;

public abstract class ObiektGry {

    protected Bitmap mObraz;
    protected Bitmap mBoom;

    protected final int mLiczbaWierszy;
    protected final int mLiczbaKolumn;

    protected final int SZEROKOSC_GRAFIKI;
    protected final int WYSOKOSC_GRAFIKI;

    protected final int SZEROKOSC_BOOM;
    protected final int WYSOKOSC_BOOM;

    protected final int mSzerokosc;
    protected final int mWysokosc;

    protected final int mBoomSzerokosc;
    protected final int mBoomWysokosc;

    protected int mX;
    protected int mY;

    protected int id;

    public ObiektGry(Bitmap obraz,Bitmap boom, int liczbaWierszy, int liczbaKolumn, int x, int y, int id)  {

        this.mBoom = boom;
        this.mObraz = obraz;
        this.mLiczbaWierszy = liczbaWierszy;
        this.mLiczbaKolumn = liczbaKolumn;

        this.mX = x;
        this.mY = y;
        this.id = id;

        this.SZEROKOSC_GRAFIKI = obraz.getWidth();
        this.WYSOKOSC_GRAFIKI = obraz.getHeight();
        this.SZEROKOSC_BOOM = boom.getWidth();
        this.WYSOKOSC_BOOM = boom.getHeight();

        this.mSzerokosc = this.SZEROKOSC_GRAFIKI / liczbaKolumn;
        this.mWysokosc = this.WYSOKOSC_GRAFIKI / liczbaWierszy;
        this.mBoomSzerokosc = this.SZEROKOSC_BOOM / 5;
        this.mBoomWysokosc = this.WYSOKOSC_BOOM / 5;
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