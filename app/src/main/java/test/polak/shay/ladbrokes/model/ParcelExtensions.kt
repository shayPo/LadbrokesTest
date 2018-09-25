package test.polak.shay.ladbrokes.model

import android.os.Parcel

inline fun Parcel.readBoolean() = readInt() != 0

inline fun Parcel.writeBoolean(value: Boolean) = writeInt(if (value) 1 else 0)