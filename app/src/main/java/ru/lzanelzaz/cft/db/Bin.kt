package ru.lzanelzaz.cft.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.lzanelzaz.cft.model.BinInfo

@Entity
data class Bin(
    @PrimaryKey val id: Int?,
    @Embedded val binInfo: BinInfo
)
