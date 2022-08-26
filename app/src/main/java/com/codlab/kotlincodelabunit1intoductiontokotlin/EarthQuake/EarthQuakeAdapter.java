package com.codlab.kotlincodelabunit1intoductiontokotlin.EarthQuake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.core.content.ContextCompat;

import com.codlab.kotlincodelabunit1intoductiontokotlin.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * {@link EarthQuakeAdapter} 知道如何為每次地震創建列表項佈局
 * 在數據源中（{@link Earthquake} 對象的列表）。
 *
 * 這些列表項佈局將提供給像 ListView 這樣的適配器視圖
 * 顯示給用戶。
 */
public class EarthQuakeAdapter extends ArrayAdapter<Earthquake> {
    /**
     * 我們用來確定的來自 USGS 服務的位置字符串部分
     * 是否存在位置偏移（“埃及開羅北部 5 公里”）。
     */
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthQuakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * 構造一個新的 {@link EarthQuakeAdapter}。
     *
     * @param 應用的 Context
     * @param地震是地震列表，是適配器的數據源
     */

   /**
            * 返回一個列表項視圖，顯示給定位置的地震信息
      * 在地震列表中。
            */

    public View getView(int position, View convertView, ViewGroup parent) {

        // 檢查是否存在我們可以重用的現有列表項視圖（稱為 convertView），
        // 否則，如果 convertView 為空，則膨脹一個新的列表項佈局。
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        // 在地震列表中查找給定位置的地震
        Earthquake currentEarthquake = getItem(position);

        // 查找具有視圖 ID 大小的 TextView
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

        // 格式化幅度以顯示小數點後 1 位
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude()); //google
        // 在那個TextView中顯示當前地震的震級
        magnitudeView.setText(formattedMagnitude);

        // 在幅度圓上設置適當的背景顏色。
        // 從 TextView 中獲取背景，這是一個 GradientDrawable。
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // 根據當前地震震級獲取合適的背景顏色
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // 設置大小圓圈的顏色
        magnitudeCircle.setColor(magnitudeColor);

        // 從地震對像中獲取原始位置字符串，
        // 格式可以是“埃及開羅 5km N of Cairo”或“Pacific-Antarctic Ridge”。
        String originalLocation = currentEarthquake.getLocation();

        // 如果原始位置字符串（即“埃及開羅 5km N”）包含
        // 一個主要位置（埃及開羅）和一個位置偏移量（該城市的北 5 公里）
        // 然後將主要位置與位置偏移分開存儲在 2 個字符串中，
        // 這樣它們就可以顯示在 2 個 TextView 中。
        String primaryLocation;
        String locationOffset;

        // 檢查原始位置字符串是否包含“ of”文本
        if (originalLocation.contains(LOCATION_SEPARATOR)) {

            // 將字符串拆分成不同的部分（作為字符串數組）
            // 基於 " of " 文本。 我們期望一個包含 2 個字符串的數組，其中
            // 第一個字符串是“5km N”，第二個字符串是“開羅，埃及”

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);

            // 位置偏移應該是 "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;

            // 主要位置應該是“埃及開羅”
            primaryLocation = parts[1];
        } else {
            // 否則，originalLocation 字符串中沒有“of”文本。
            // 因此，將默認位置偏移設置為“靠近”。
            locationOffset = getContext().getString(R.string.near_the);
            // 主要位置將是完整的位置字符串“Pacific-Antarctic Ridge”。
            primaryLocation = originalLocation;
        }
            // 查找帶有視圖 ID 位置的 TextView
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        // 在那個TextView中顯示當前地震的位置
        primaryLocationView.setText(primaryLocation);
        // 查找帶有視圖 ID 位置偏移的 TextView
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        // 在那個TextView中顯示當前地震的位置偏移
        locationOffsetView.setText(locationOffset);
        // 從地震的毫秒數創建一個新的 Date 對象
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // 查找帶有視圖 ID 日期的 TextView
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // 格式化日期字符串（即“Mar 3, 1984”）
        String formattedDate = formatDate(dateObject);
        // 在那個 TextView 中顯示當前地震的日期
        dateView.setText(formattedDate);
        // 查找帶有視圖 ID 時間的 TextView
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // 格式化時間字符串（即“4:30PM”）
        String formattedTime = formatTime(dateObject);
        // 在那個TextView中顯示當前地震的時間
        timeView.setText(formattedTime);
        // 返回現在顯示適當數據的列表項視圖
        return listItemView;
    }
    /**
     * 根據地震強度返回震級圈的顏色。
     *
     * @param magnitude
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * 返回顯示 1 個小數位的格式化幅度字符串（即“3.2”）
     * 來自十進制大小值。
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0"); // google
        return magnitudeFormat.format(magnitude);
    }
    /**
     * 從 Date 對象返回格式化的日期字符串（即“Mar 3, 1984”）。
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("LLL dd, yyyy");
        return timeFormat.format(dateObject);
    }
    /**
     * 從 Date 對象返回格式化的日期字符串（即“Mar 3, 1984”）。
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


}
