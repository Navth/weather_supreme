package org.breezyweather.ui.main.adapters.location

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import breezyweather.domain.location.model.Location
import org.breezyweather.databinding.ItemLocationCardBinding
import org.breezyweather.sources.SourceManager
import org.breezyweather.ui.common.adapters.SyncListAdapter
import org.breezyweather.ui.theme.resource.ResourcesProviderFactory
import org.breezyweather.ui.theme.resource.providers.ResourceProvider

/**
 * Location adapter.
 */
class LocationAdapter(
    private val mContext: Context,
    locationList: List<Location>,
    selectedId: String?,
    private val sourceManager: SourceManager,
    private val mClickListener: (String) -> Unit,
    private val mDragListener: (LocationHolder) -> Unit,
) : SyncListAdapter<LocationModel, LocationHolder>(
    ArrayList(),
    object : DiffUtil.ItemCallback<LocationModel>() {
        override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            return oldItem.areItemsTheSame(newItem)
        }

        override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }
    }
) {
    private val mResourceProvider: ResourceProvider = ResourcesProviderFactory.newInstance

    init {
        update(locationList, selectedId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        return LocationHolder(
            ItemLocationCardBinding.inflate(LayoutInflater.from(parent.context)),
            mClickListener,
            mDragListener
        )
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.onBindView(mContext, getItem(position), mResourceProvider)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int, payloads: List<Any>) {
        holder.onBindView(mContext, getItem(position), mResourceProvider)
    }

    fun update(selectedId: String?) {
        val modelList: MutableList<LocationModel> = ArrayList(itemCount)
        for (model in currentList) {
            modelList.add(
                LocationModel(
                    mContext,
                    model.location,
                    model.location.formattedId == selectedId
                )
            )
        }
        submitList(modelList)
    }

    fun update(newList: List<Location>, selectedId: String?) {
        val modelList: MutableList<LocationModel> = ArrayList(newList.size)
        for (l in newList) {
            modelList.add(
                LocationModel(
                    mContext,
                    l,
                    l.formattedId == selectedId
                )
            )
        }
        submitList(modelList)
    }

    fun update(from: Int, to: Int) {
        submitMove(from, to)
    }
}
