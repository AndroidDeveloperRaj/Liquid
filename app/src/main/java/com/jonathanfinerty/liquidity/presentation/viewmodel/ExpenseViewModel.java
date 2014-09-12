package com.jonathanfinerty.liquidity.presentation.viewmodel;

import android.text.format.DateUtils;

import com.jonathanfinerty.liquidity.domain.Expense;

import java.text.DateFormat;
import java.util.Calendar;

public class ExpenseViewModel implements Comparable<ExpenseViewModel> {

    private long id;

    private int value;

    private Calendar time;

    public ExpenseViewModel(Expense expense) {
        id = expense.getId();
        value = expense.getValue();
        time = expense.getTime();
    }

    public String getHumanReadableValue() {
        float decimalisedTotal = ((float) value) / 100f;
        return "£" + String.format("%.2f", decimalisedTotal);
    }

    public CharSequence getHumanReadableRelativeTime() {
        return DateUtils.getRelativeTimeSpanString(
                time.getTimeInMillis(),
                Calendar.getInstance().getTimeInMillis(),
                DateUtils.SECOND_IN_MILLIS,
                0
        );
    }

    public CharSequence getHumanReadableTime() {
        return DateFormat.getDateInstance().format(time.getTime());
    }

    @Override
    public int compareTo(ExpenseViewModel expenseViewModel) {

        long difference = time.getTimeInMillis() - expenseViewModel.time.getTimeInMillis();

        if (difference < 0) {
            return -1;
        } else if (difference == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public long getId() {
        return id;
    }

    public Calendar getCalendar() {
        return time;
    }
}
