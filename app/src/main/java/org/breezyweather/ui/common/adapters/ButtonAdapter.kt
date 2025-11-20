package org.breezyweather.ui.common.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.breezyweather.R

class ButtonAdapter @JvmOverloads constructor(
    private val buttonList: MutableList<Button>,
    private val listener: ((checked: Boolean, oldPosition: Int, newPosition: Int) -> Boolean)? = null,
    private var checkedIndex: Int = UNCHECKABLE_INDEX,
) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val buttonView: MaterialButton = itemView.findViewById(R.id.item_button)

        init {
            buttonView.setOnClickListener {
                var consumed = false
                if (listener != null) {
                    consumed = listener.invoke(
                        !buttonView.isChecked,
                        checkedIndex,
                        bindingAdapterPosition
                    )
                }
                if (!consumed && checkedIndex != bindingAdapterPosition) {
                    val i = checkedIndex
                    checkedIndex = bindingAdapterPosition
                    notifyItemChanged(i)
                    notifyItemChanged(checkedIndex)
                }
            }
        }

        fun onBindView(button: Button, checked: Boolean) {
            buttonView.apply {
                text = button.name
                isChecked = checked
            }
        }
    }

    interface Button {
        val name: String
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_button, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(buttonList[position], position == checkedIndex)
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

    companion object {
        const val UNCHECKABLE_INDEX = -1
    }
}
