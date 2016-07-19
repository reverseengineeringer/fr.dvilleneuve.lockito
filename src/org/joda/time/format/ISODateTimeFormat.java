package org.joda.time.format;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.DateTimeFieldType;

public class ISODateTimeFormat
{
  private static void appendSeparator(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramDateTimeFormatterBuilder.appendLiteral('-');
    }
  }
  
  public static DateTimeFormatter basicDate()
  {
    return Constants.bd;
  }
  
  public static DateTimeFormatter basicDateTime()
  {
    return Constants.bdt;
  }
  
  public static DateTimeFormatter basicDateTimeNoMillis()
  {
    return Constants.bdtx;
  }
  
  public static DateTimeFormatter basicOrdinalDate()
  {
    return Constants.bod;
  }
  
  public static DateTimeFormatter basicOrdinalDateTime()
  {
    return Constants.bodt;
  }
  
  public static DateTimeFormatter basicOrdinalDateTimeNoMillis()
  {
    return Constants.bodtx;
  }
  
  public static DateTimeFormatter basicTTime()
  {
    return Constants.btt;
  }
  
  public static DateTimeFormatter basicTTimeNoMillis()
  {
    return Constants.bttx;
  }
  
  public static DateTimeFormatter basicTime()
  {
    return Constants.bt;
  }
  
  public static DateTimeFormatter basicTimeNoMillis()
  {
    return Constants.btx;
  }
  
  public static DateTimeFormatter basicWeekDate()
  {
    return Constants.bwd;
  }
  
  public static DateTimeFormatter basicWeekDateTime()
  {
    return Constants.bwdt;
  }
  
  public static DateTimeFormatter basicWeekDateTimeNoMillis()
  {
    return Constants.bwdtx;
  }
  
  private static void checkNotStrictISO(Collection<DateTimeFieldType> paramCollection, boolean paramBoolean)
  {
    if (paramBoolean) {
      throw new IllegalArgumentException("No valid ISO8601 format for fields: " + paramCollection);
    }
  }
  
  public static DateTimeFormatter date()
  {
    return yearMonthDay();
  }
  
  private static boolean dateByMonth(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection<DateTimeFieldType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramCollection.remove(DateTimeFieldType.year()))
    {
      paramDateTimeFormatterBuilder.append(Constants.ye);
      if (paramCollection.remove(DateTimeFieldType.monthOfYear())) {
        if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
        {
          appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
          paramDateTimeFormatterBuilder.appendMonthOfYear(2);
          appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
          paramDateTimeFormatterBuilder.appendDayOfMonth(2);
        }
      }
    }
    do
    {
      return false;
      paramDateTimeFormatterBuilder.appendLiteral('-');
      paramDateTimeFormatterBuilder.appendMonthOfYear(2);
      return true;
      if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
      {
        checkNotStrictISO(paramCollection, paramBoolean2);
        paramDateTimeFormatterBuilder.appendLiteral('-');
        paramDateTimeFormatterBuilder.appendLiteral('-');
        paramDateTimeFormatterBuilder.appendDayOfMonth(2);
        return false;
      }
      return true;
      if (paramCollection.remove(DateTimeFieldType.monthOfYear()))
      {
        paramDateTimeFormatterBuilder.appendLiteral('-');
        paramDateTimeFormatterBuilder.appendLiteral('-');
        paramDateTimeFormatterBuilder.appendMonthOfYear(2);
        if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
        {
          appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
          paramDateTimeFormatterBuilder.appendDayOfMonth(2);
          return false;
        }
        return true;
      }
    } while (!paramCollection.remove(DateTimeFieldType.dayOfMonth()));
    paramDateTimeFormatterBuilder.appendLiteral('-');
    paramDateTimeFormatterBuilder.appendLiteral('-');
    paramDateTimeFormatterBuilder.appendLiteral('-');
    paramDateTimeFormatterBuilder.appendDayOfMonth(2);
    return false;
  }
  
  private static boolean dateByOrdinal(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection<DateTimeFieldType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramCollection.remove(DateTimeFieldType.year()))
    {
      paramDateTimeFormatterBuilder.append(Constants.ye);
      if (paramCollection.remove(DateTimeFieldType.dayOfYear()))
      {
        appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
        paramDateTimeFormatterBuilder.appendDayOfYear(3);
      }
    }
    while (!paramCollection.remove(DateTimeFieldType.dayOfYear()))
    {
      return false;
      return true;
    }
    paramDateTimeFormatterBuilder.appendLiteral('-');
    paramDateTimeFormatterBuilder.appendDayOfYear(3);
    return false;
  }
  
  private static boolean dateByWeek(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection<DateTimeFieldType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramCollection.remove(DateTimeFieldType.weekyear()))
    {
      paramDateTimeFormatterBuilder.append(Constants.we);
      if (paramCollection.remove(DateTimeFieldType.weekOfWeekyear()))
      {
        appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
        paramDateTimeFormatterBuilder.appendLiteral('W');
        paramDateTimeFormatterBuilder.appendWeekOfWeekyear(2);
        if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
        {
          appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
          paramDateTimeFormatterBuilder.appendDayOfWeek(1);
        }
      }
    }
    do
    {
      return false;
      return true;
      if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
      {
        checkNotStrictISO(paramCollection, paramBoolean2);
        appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
        paramDateTimeFormatterBuilder.appendLiteral('W');
        paramDateTimeFormatterBuilder.appendLiteral('-');
        paramDateTimeFormatterBuilder.appendDayOfWeek(1);
        return false;
      }
      return true;
      if (paramCollection.remove(DateTimeFieldType.weekOfWeekyear()))
      {
        paramDateTimeFormatterBuilder.appendLiteral('-');
        paramDateTimeFormatterBuilder.appendLiteral('W');
        paramDateTimeFormatterBuilder.appendWeekOfWeekyear(2);
        if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
        {
          appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
          paramDateTimeFormatterBuilder.appendDayOfWeek(1);
          return false;
        }
        return true;
      }
    } while (!paramCollection.remove(DateTimeFieldType.dayOfWeek()));
    paramDateTimeFormatterBuilder.appendLiteral('-');
    paramDateTimeFormatterBuilder.appendLiteral('W');
    paramDateTimeFormatterBuilder.appendLiteral('-');
    paramDateTimeFormatterBuilder.appendDayOfWeek(1);
    return false;
  }
  
  public static DateTimeFormatter dateElementParser()
  {
    return Constants.dpe;
  }
  
  public static DateTimeFormatter dateHour()
  {
    return Constants.dh;
  }
  
  public static DateTimeFormatter dateHourMinute()
  {
    return Constants.dhm;
  }
  
  public static DateTimeFormatter dateHourMinuteSecond()
  {
    return Constants.dhms;
  }
  
  public static DateTimeFormatter dateHourMinuteSecondFraction()
  {
    return Constants.dhmsf;
  }
  
  public static DateTimeFormatter dateHourMinuteSecondMillis()
  {
    return Constants.dhmsl;
  }
  
  public static DateTimeFormatter dateOptionalTimeParser()
  {
    return Constants.dotp;
  }
  
  public static DateTimeFormatter dateParser()
  {
    return Constants.dp;
  }
  
  public static DateTimeFormatter dateTime()
  {
    return Constants.dt;
  }
  
  public static DateTimeFormatter dateTimeNoMillis()
  {
    return Constants.dtx;
  }
  
  public static DateTimeFormatter dateTimeParser()
  {
    return Constants.dtp;
  }
  
  public static DateTimeFormatter forFields(Collection<DateTimeFieldType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramCollection == null) || (paramCollection.size() == 0)) {
      throw new IllegalArgumentException("The fields must not be null or empty");
    }
    HashSet localHashSet = new HashSet(paramCollection);
    int i = localHashSet.size();
    boolean bool1 = false;
    DateTimeFormatterBuilder localDateTimeFormatterBuilder = new DateTimeFormatterBuilder();
    if (localHashSet.contains(DateTimeFieldType.monthOfYear()))
    {
      bool1 = dateByMonth(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
      if (localHashSet.size() >= i) {
        break label303;
      }
    }
    label303:
    for (boolean bool2 = true;; bool2 = false)
    {
      time(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2, bool1, bool2);
      if (localDateTimeFormatterBuilder.canBuildFormatter()) {
        break label309;
      }
      throw new IllegalArgumentException("No valid format for fields: " + paramCollection);
      if (localHashSet.contains(DateTimeFieldType.dayOfYear()))
      {
        bool1 = dateByOrdinal(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
        break;
      }
      if (localHashSet.contains(DateTimeFieldType.weekOfWeekyear()))
      {
        bool1 = dateByWeek(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
        break;
      }
      if (localHashSet.contains(DateTimeFieldType.dayOfMonth()))
      {
        bool1 = dateByMonth(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
        break;
      }
      if (localHashSet.contains(DateTimeFieldType.dayOfWeek()))
      {
        bool1 = dateByWeek(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
        break;
      }
      if (localHashSet.remove(DateTimeFieldType.year()))
      {
        localDateTimeFormatterBuilder.append(Constants.ye);
        bool1 = true;
        break;
      }
      if (!localHashSet.remove(DateTimeFieldType.weekyear())) {
        break;
      }
      localDateTimeFormatterBuilder.append(Constants.we);
      bool1 = true;
      break;
    }
    try
    {
      label309:
      paramCollection.retainAll(localHashSet);
      return localDateTimeFormatterBuilder.toFormatter();
    }
    catch (UnsupportedOperationException paramCollection)
    {
      for (;;) {}
    }
  }
  
  public static DateTimeFormatter hour()
  {
    return Constants.hde;
  }
  
  public static DateTimeFormatter hourMinute()
  {
    return Constants.hm;
  }
  
  public static DateTimeFormatter hourMinuteSecond()
  {
    return Constants.hms;
  }
  
  public static DateTimeFormatter hourMinuteSecondFraction()
  {
    return Constants.hmsf;
  }
  
  public static DateTimeFormatter hourMinuteSecondMillis()
  {
    return Constants.hmsl;
  }
  
  public static DateTimeFormatter localDateOptionalTimeParser()
  {
    return Constants.ldotp;
  }
  
  public static DateTimeFormatter localDateParser()
  {
    return Constants.ldp;
  }
  
  public static DateTimeFormatter localTimeParser()
  {
    return Constants.ltp;
  }
  
  public static DateTimeFormatter ordinalDate()
  {
    return Constants.od;
  }
  
  public static DateTimeFormatter ordinalDateTime()
  {
    return Constants.odt;
  }
  
  public static DateTimeFormatter ordinalDateTimeNoMillis()
  {
    return Constants.odtx;
  }
  
  public static DateTimeFormatter tTime()
  {
    return Constants.tt;
  }
  
  public static DateTimeFormatter tTimeNoMillis()
  {
    return Constants.ttx;
  }
  
  public static DateTimeFormatter time()
  {
    return Constants.t;
  }
  
  private static void time(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection<DateTimeFieldType> paramCollection, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    boolean bool1 = paramCollection.remove(DateTimeFieldType.hourOfDay());
    boolean bool2 = paramCollection.remove(DateTimeFieldType.minuteOfHour());
    boolean bool3 = paramCollection.remove(DateTimeFieldType.secondOfMinute());
    boolean bool4 = paramCollection.remove(DateTimeFieldType.millisOfSecond());
    if ((!bool1) && (!bool2) && (!bool3) && (!bool4)) {}
    label175:
    label207:
    label356:
    label381:
    label401:
    label414:
    for (;;)
    {
      return;
      if ((bool1) || (bool2) || (bool3) || (bool4))
      {
        if ((paramBoolean2) && (paramBoolean3)) {
          throw new IllegalArgumentException("No valid ISO8601 format for fields because Date was reduced precision: " + paramCollection);
        }
        if (paramBoolean4) {
          paramDateTimeFormatterBuilder.appendLiteral('T');
        }
      }
      if (((bool1) && (bool2) && (bool3)) || ((bool1) && (!bool3) && (!bool4)))
      {
        if (!bool1) {
          break label356;
        }
        paramDateTimeFormatterBuilder.appendHourOfDay(2);
        if ((paramBoolean1) && (bool1) && (bool2)) {
          paramDateTimeFormatterBuilder.appendLiteral(':');
        }
        if (!bool2) {
          break label381;
        }
        paramDateTimeFormatterBuilder.appendMinuteOfHour(2);
        if ((paramBoolean1) && (bool2) && (bool3)) {
          paramDateTimeFormatterBuilder.appendLiteral(':');
        }
        if (!bool3) {
          break label401;
        }
        paramDateTimeFormatterBuilder.appendSecondOfMinute(2);
      }
      for (;;)
      {
        if (!bool4) {
          break label414;
        }
        paramDateTimeFormatterBuilder.appendLiteral('.');
        paramDateTimeFormatterBuilder.appendMillisOfSecond(3);
        return;
        if ((paramBoolean2) && (paramBoolean4)) {
          throw new IllegalArgumentException("No valid ISO8601 format for fields because Time was truncated: " + paramCollection);
        }
        if (((!bool1) && (((bool2) && (bool3)) || ((bool2) && (!bool4)) || (bool3))) || (!paramBoolean2)) {
          break;
        }
        throw new IllegalArgumentException("No valid ISO8601 format for fields: " + paramCollection);
        if ((!bool2) && (!bool3) && (!bool4)) {
          break label175;
        }
        paramDateTimeFormatterBuilder.appendLiteral('-');
        break label175;
        if ((!bool3) && (!bool4)) {
          break label207;
        }
        paramDateTimeFormatterBuilder.appendLiteral('-');
        break label207;
        if (bool4) {
          paramDateTimeFormatterBuilder.appendLiteral('-');
        }
      }
    }
  }
  
  public static DateTimeFormatter timeElementParser()
  {
    return Constants.tpe;
  }
  
  public static DateTimeFormatter timeNoMillis()
  {
    return Constants.tx;
  }
  
  public static DateTimeFormatter timeParser()
  {
    return Constants.tp;
  }
  
  public static DateTimeFormatter weekDate()
  {
    return Constants.wwd;
  }
  
  public static DateTimeFormatter weekDateTime()
  {
    return Constants.wdt;
  }
  
  public static DateTimeFormatter weekDateTimeNoMillis()
  {
    return Constants.wdtx;
  }
  
  public static DateTimeFormatter weekyear()
  {
    return Constants.we;
  }
  
  public static DateTimeFormatter weekyearWeek()
  {
    return Constants.ww;
  }
  
  public static DateTimeFormatter weekyearWeekDay()
  {
    return Constants.wwd;
  }
  
  public static DateTimeFormatter year()
  {
    return Constants.ye;
  }
  
  public static DateTimeFormatter yearMonth()
  {
    return Constants.ym;
  }
  
  public static DateTimeFormatter yearMonthDay()
  {
    return Constants.ymd;
  }
  
  static final class Constants
  {
    private static final DateTimeFormatter bd;
    private static final DateTimeFormatter bdt;
    private static final DateTimeFormatter bdtx;
    private static final DateTimeFormatter bod;
    private static final DateTimeFormatter bodt;
    private static final DateTimeFormatter bodtx;
    private static final DateTimeFormatter bt;
    private static final DateTimeFormatter btt;
    private static final DateTimeFormatter bttx;
    private static final DateTimeFormatter btx;
    private static final DateTimeFormatter bwd;
    private static final DateTimeFormatter bwdt;
    private static final DateTimeFormatter bwdtx;
    private static final DateTimeFormatter dh;
    private static final DateTimeFormatter dhm;
    private static final DateTimeFormatter dhms;
    private static final DateTimeFormatter dhmsf;
    private static final DateTimeFormatter dhmsl;
    private static final DateTimeFormatter dme;
    private static final DateTimeFormatter dotp = dateOptionalTimeParser();
    private static final DateTimeFormatter dp;
    private static final DateTimeFormatter dpe;
    private static final DateTimeFormatter dt;
    private static final DateTimeFormatter dtp;
    private static final DateTimeFormatter dtx;
    private static final DateTimeFormatter dwe;
    private static final DateTimeFormatter dye;
    private static final DateTimeFormatter fse;
    private static final DateTimeFormatter hde;
    private static final DateTimeFormatter hm;
    private static final DateTimeFormatter hms;
    private static final DateTimeFormatter hmsf;
    private static final DateTimeFormatter hmsl;
    private static final DateTimeFormatter ldotp = localDateOptionalTimeParser();
    private static final DateTimeFormatter ldp;
    private static final DateTimeFormatter lte;
    private static final DateTimeFormatter ltp;
    private static final DateTimeFormatter mhe;
    private static final DateTimeFormatter mye;
    private static final DateTimeFormatter od;
    private static final DateTimeFormatter odt;
    private static final DateTimeFormatter odtx;
    private static final DateTimeFormatter sme;
    private static final DateTimeFormatter t;
    private static final DateTimeFormatter tp;
    private static final DateTimeFormatter tpe;
    private static final DateTimeFormatter tt;
    private static final DateTimeFormatter ttx;
    private static final DateTimeFormatter tx;
    private static final DateTimeFormatter wdt;
    private static final DateTimeFormatter wdtx;
    private static final DateTimeFormatter we;
    private static final DateTimeFormatter ww;
    private static final DateTimeFormatter wwd;
    private static final DateTimeFormatter wwe;
    private static final DateTimeFormatter ye = ;
    private static final DateTimeFormatter ym;
    private static final DateTimeFormatter ymd;
    private static final DateTimeFormatter ze;
    
    static
    {
      mye = monthElement();
      dme = dayOfMonthElement();
      we = weekyearElement();
      wwe = weekElement();
      dwe = dayOfWeekElement();
      dye = dayOfYearElement();
      hde = hourElement();
      mhe = minuteElement();
      sme = secondElement();
      fse = fractionElement();
      ze = offsetElement();
      lte = literalTElement();
      ym = yearMonth();
      ymd = yearMonthDay();
      ww = weekyearWeek();
      wwd = weekyearWeekDay();
      hm = hourMinute();
      hms = hourMinuteSecond();
      hmsl = hourMinuteSecondMillis();
      hmsf = hourMinuteSecondFraction();
      dh = dateHour();
      dhm = dateHourMinute();
      dhms = dateHourMinuteSecond();
      dhmsl = dateHourMinuteSecondMillis();
      dhmsf = dateHourMinuteSecondFraction();
      t = time();
      tx = timeNoMillis();
      tt = tTime();
      ttx = tTimeNoMillis();
      dt = dateTime();
      dtx = dateTimeNoMillis();
      wdt = weekDateTime();
      wdtx = weekDateTimeNoMillis();
      od = ordinalDate();
      odt = ordinalDateTime();
      odtx = ordinalDateTimeNoMillis();
      bd = basicDate();
      bt = basicTime();
      btx = basicTimeNoMillis();
      btt = basicTTime();
      bttx = basicTTimeNoMillis();
      bdt = basicDateTime();
      bdtx = basicDateTimeNoMillis();
      bod = basicOrdinalDate();
      bodt = basicOrdinalDateTime();
      bodtx = basicOrdinalDateTimeNoMillis();
      bwd = basicWeekDate();
      bwdt = basicWeekDateTime();
      bwdtx = basicWeekDateTimeNoMillis();
      dpe = dateElementParser();
      tpe = timeElementParser();
      dp = dateParser();
      ldp = localDateParser();
      tp = timeParser();
      ltp = localTimeParser();
      dtp = dateTimeParser();
    }
    
    private static DateTimeFormatter basicDate()
    {
      if (bd == null) {
        return new DateTimeFormatterBuilder().appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2).toFormatter();
      }
      return bd;
    }
    
    private static DateTimeFormatter basicDateTime()
    {
      if (bdt == null) {
        return new DateTimeFormatterBuilder().append(basicDate()).append(basicTTime()).toFormatter();
      }
      return bdt;
    }
    
    private static DateTimeFormatter basicDateTimeNoMillis()
    {
      if (bdtx == null) {
        return new DateTimeFormatterBuilder().append(basicDate()).append(basicTTimeNoMillis()).toFormatter();
      }
      return bdtx;
    }
    
    private static DateTimeFormatter basicOrdinalDate()
    {
      if (bod == null) {
        return new DateTimeFormatterBuilder().appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.dayOfYear(), 3).toFormatter();
      }
      return bod;
    }
    
    private static DateTimeFormatter basicOrdinalDateTime()
    {
      if (bodt == null) {
        return new DateTimeFormatterBuilder().append(basicOrdinalDate()).append(basicTTime()).toFormatter();
      }
      return bodt;
    }
    
    private static DateTimeFormatter basicOrdinalDateTimeNoMillis()
    {
      if (bodtx == null) {
        return new DateTimeFormatterBuilder().append(basicOrdinalDate()).append(basicTTimeNoMillis()).toFormatter();
      }
      return bodtx;
    }
    
    private static DateTimeFormatter basicTTime()
    {
      if (btt == null) {
        return new DateTimeFormatterBuilder().append(literalTElement()).append(basicTime()).toFormatter();
      }
      return btt;
    }
    
    private static DateTimeFormatter basicTTimeNoMillis()
    {
      if (bttx == null) {
        return new DateTimeFormatterBuilder().append(literalTElement()).append(basicTimeNoMillis()).toFormatter();
      }
      return bttx;
    }
    
    private static DateTimeFormatter basicTime()
    {
      if (bt == null) {
        return new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendLiteral('.').appendFractionOfSecond(3, 9).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
      }
      return bt;
    }
    
    private static DateTimeFormatter basicTimeNoMillis()
    {
      if (btx == null) {
        return new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
      }
      return btx;
    }
    
    private static DateTimeFormatter basicWeekDate()
    {
      if (bwd == null) {
        return new DateTimeFormatterBuilder().appendWeekyear(4, 4).appendLiteral('W').appendFixedDecimal(DateTimeFieldType.weekOfWeekyear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfWeek(), 1).toFormatter();
      }
      return bwd;
    }
    
    private static DateTimeFormatter basicWeekDateTime()
    {
      if (bwdt == null) {
        return new DateTimeFormatterBuilder().append(basicWeekDate()).append(basicTTime()).toFormatter();
      }
      return bwdt;
    }
    
    private static DateTimeFormatter basicWeekDateTimeNoMillis()
    {
      if (bwdtx == null) {
        return new DateTimeFormatterBuilder().append(basicWeekDate()).append(basicTTimeNoMillis()).toFormatter();
      }
      return bwdtx;
    }
    
    private static DateTimeFormatter dateElementParser()
    {
      if (dpe == null) {
        return new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(yearElement()).appendOptional(new DateTimeFormatterBuilder().append(monthElement()).appendOptional(dayOfMonthElement().getParser()).toParser()).toParser(), new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).appendOptional(dayOfWeekElement().getParser()).toParser(), new DateTimeFormatterBuilder().append(yearElement()).append(dayOfYearElement()).toParser() }).toFormatter();
      }
      return dpe;
    }
    
    private static DateTimeFormatter dateHour()
    {
      if (dh == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(literalTElement()).append(ISODateTimeFormat.hour()).toFormatter();
      }
      return dh;
    }
    
    private static DateTimeFormatter dateHourMinute()
    {
      if (dhm == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinute()).toFormatter();
      }
      return dhm;
    }
    
    private static DateTimeFormatter dateHourMinuteSecond()
    {
      if (dhms == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinuteSecond()).toFormatter();
      }
      return dhms;
    }
    
    private static DateTimeFormatter dateHourMinuteSecondFraction()
    {
      if (dhmsf == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinuteSecondFraction()).toFormatter();
      }
      return dhmsf;
    }
    
    private static DateTimeFormatter dateHourMinuteSecondMillis()
    {
      if (dhmsl == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinuteSecondMillis()).toFormatter();
      }
      return dhmsl;
    }
    
    private static DateTimeFormatter dateOptionalTimeParser()
    {
      if (dotp == null)
      {
        DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').appendOptional(timeElementParser().getParser()).appendOptional(offsetElement().getParser()).toParser();
        return new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(localDateTimeParser).toFormatter();
      }
      return dotp;
    }
    
    private static DateTimeFormatter dateParser()
    {
      if (dp == null)
      {
        DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').append(offsetElement()).toParser();
        return new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(localDateTimeParser).toFormatter();
      }
      return dp;
    }
    
    private static DateTimeFormatter dateTime()
    {
      if (dt == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(tTime()).toFormatter();
      }
      return dt;
    }
    
    private static DateTimeFormatter dateTimeNoMillis()
    {
      if (dtx == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.date()).append(tTimeNoMillis()).toFormatter();
      }
      return dtx;
    }
    
    private static DateTimeFormatter dateTimeParser()
    {
      if (dtp == null)
      {
        DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').append(timeElementParser()).appendOptional(offsetElement().getParser()).toParser();
        return new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { localDateTimeParser, dateOptionalTimeParser().getParser() }).toFormatter();
      }
      return dtp;
    }
    
    private static DateTimeFormatter dayOfMonthElement()
    {
      if (dme == null) {
        return new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfMonth(2).toFormatter();
      }
      return dme;
    }
    
    private static DateTimeFormatter dayOfWeekElement()
    {
      if (dwe == null) {
        return new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfWeek(1).toFormatter();
      }
      return dwe;
    }
    
    private static DateTimeFormatter dayOfYearElement()
    {
      if (dye == null) {
        return new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfYear(3).toFormatter();
      }
      return dye;
    }
    
    private static DateTimeFormatter fractionElement()
    {
      if (fse == null) {
        return new DateTimeFormatterBuilder().appendLiteral('.').appendFractionOfSecond(3, 9).toFormatter();
      }
      return fse;
    }
    
    private static DateTimeFormatter hourElement()
    {
      if (hde == null) {
        return new DateTimeFormatterBuilder().appendHourOfDay(2).toFormatter();
      }
      return hde;
    }
    
    private static DateTimeFormatter hourMinute()
    {
      if (hm == null) {
        return new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).toFormatter();
      }
      return hm;
    }
    
    private static DateTimeFormatter hourMinuteSecond()
    {
      if (hms == null) {
        return new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).toFormatter();
      }
      return hms;
    }
    
    private static DateTimeFormatter hourMinuteSecondFraction()
    {
      if (hmsf == null) {
        return new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).append(fractionElement()).toFormatter();
      }
      return hmsf;
    }
    
    private static DateTimeFormatter hourMinuteSecondMillis()
    {
      if (hmsl == null) {
        return new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).appendLiteral('.').appendFractionOfSecond(3, 3).toFormatter();
      }
      return hmsl;
    }
    
    private static DateTimeFormatter literalTElement()
    {
      if (lte == null) {
        return new DateTimeFormatterBuilder().appendLiteral('T').toFormatter();
      }
      return lte;
    }
    
    private static DateTimeFormatter localDateOptionalTimeParser()
    {
      if (ldotp == null)
      {
        DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').append(timeElementParser()).toParser();
        return new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(localDateTimeParser).toFormatter().withZoneUTC();
      }
      return ldotp;
    }
    
    private static DateTimeFormatter localDateParser()
    {
      if (ldp == null) {
        return dateElementParser().withZoneUTC();
      }
      return ldp;
    }
    
    private static DateTimeFormatter localTimeParser()
    {
      if (ltp == null) {
        return new DateTimeFormatterBuilder().appendOptional(literalTElement().getParser()).append(timeElementParser()).toFormatter().withZoneUTC();
      }
      return ltp;
    }
    
    private static DateTimeFormatter minuteElement()
    {
      if (mhe == null) {
        return new DateTimeFormatterBuilder().appendLiteral(':').appendMinuteOfHour(2).toFormatter();
      }
      return mhe;
    }
    
    private static DateTimeFormatter monthElement()
    {
      if (mye == null) {
        return new DateTimeFormatterBuilder().appendLiteral('-').appendMonthOfYear(2).toFormatter();
      }
      return mye;
    }
    
    private static DateTimeFormatter offsetElement()
    {
      if (ze == null) {
        return new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
      }
      return ze;
    }
    
    private static DateTimeFormatter ordinalDate()
    {
      if (od == null) {
        return new DateTimeFormatterBuilder().append(yearElement()).append(dayOfYearElement()).toFormatter();
      }
      return od;
    }
    
    private static DateTimeFormatter ordinalDateTime()
    {
      if (odt == null) {
        return new DateTimeFormatterBuilder().append(ordinalDate()).append(tTime()).toFormatter();
      }
      return odt;
    }
    
    private static DateTimeFormatter ordinalDateTimeNoMillis()
    {
      if (odtx == null) {
        return new DateTimeFormatterBuilder().append(ordinalDate()).append(tTimeNoMillis()).toFormatter();
      }
      return odtx;
    }
    
    private static DateTimeFormatter secondElement()
    {
      if (sme == null) {
        return new DateTimeFormatterBuilder().appendLiteral(':').appendSecondOfMinute(2).toFormatter();
      }
      return sme;
    }
    
    private static DateTimeFormatter tTime()
    {
      if (tt == null) {
        return new DateTimeFormatterBuilder().append(literalTElement()).append(time()).toFormatter();
      }
      return tt;
    }
    
    private static DateTimeFormatter tTimeNoMillis()
    {
      if (ttx == null) {
        return new DateTimeFormatterBuilder().append(literalTElement()).append(timeNoMillis()).toFormatter();
      }
      return ttx;
    }
    
    private static DateTimeFormatter time()
    {
      if (t == null) {
        return new DateTimeFormatterBuilder().append(hourMinuteSecondFraction()).append(offsetElement()).toFormatter();
      }
      return t;
    }
    
    private static DateTimeFormatter timeElementParser()
    {
      if (tpe == null)
      {
        DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().appendLiteral('.').toParser(), new DateTimeFormatterBuilder().appendLiteral(',').toParser() }).toParser();
        return new DateTimeFormatterBuilder().append(hourElement()).append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(minuteElement()).append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(secondElement()).appendOptional(new DateTimeFormatterBuilder().append(localDateTimeParser).appendFractionOfSecond(1, 9).toParser()).toParser(), new DateTimeFormatterBuilder().append(localDateTimeParser).appendFractionOfMinute(1, 9).toParser(), null }).toParser(), new DateTimeFormatterBuilder().append(localDateTimeParser).appendFractionOfHour(1, 9).toParser(), null }).toFormatter();
      }
      return tpe;
    }
    
    private static DateTimeFormatter timeNoMillis()
    {
      if (tx == null) {
        return new DateTimeFormatterBuilder().append(hourMinuteSecond()).append(offsetElement()).toFormatter();
      }
      return tx;
    }
    
    private static DateTimeFormatter timeParser()
    {
      if (tp == null) {
        return new DateTimeFormatterBuilder().appendOptional(literalTElement().getParser()).append(timeElementParser()).appendOptional(offsetElement().getParser()).toFormatter();
      }
      return tp;
    }
    
    private static DateTimeFormatter weekDateTime()
    {
      if (wdt == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.weekDate()).append(tTime()).toFormatter();
      }
      return wdt;
    }
    
    private static DateTimeFormatter weekDateTimeNoMillis()
    {
      if (wdtx == null) {
        return new DateTimeFormatterBuilder().append(ISODateTimeFormat.weekDate()).append(tTimeNoMillis()).toFormatter();
      }
      return wdtx;
    }
    
    private static DateTimeFormatter weekElement()
    {
      if (wwe == null) {
        return new DateTimeFormatterBuilder().appendLiteral("-W").appendWeekOfWeekyear(2).toFormatter();
      }
      return wwe;
    }
    
    private static DateTimeFormatter weekyearElement()
    {
      if (we == null) {
        return new DateTimeFormatterBuilder().appendWeekyear(4, 9).toFormatter();
      }
      return we;
    }
    
    private static DateTimeFormatter weekyearWeek()
    {
      if (ww == null) {
        return new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).toFormatter();
      }
      return ww;
    }
    
    private static DateTimeFormatter weekyearWeekDay()
    {
      if (wwd == null) {
        return new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).append(dayOfWeekElement()).toFormatter();
      }
      return wwd;
    }
    
    private static DateTimeFormatter yearElement()
    {
      if (ye == null) {
        return new DateTimeFormatterBuilder().appendYear(4, 9).toFormatter();
      }
      return ye;
    }
    
    private static DateTimeFormatter yearMonth()
    {
      if (ym == null) {
        return new DateTimeFormatterBuilder().append(yearElement()).append(monthElement()).toFormatter();
      }
      return ym;
    }
    
    private static DateTimeFormatter yearMonthDay()
    {
      if (ymd == null) {
        return new DateTimeFormatterBuilder().append(yearElement()).append(monthElement()).append(dayOfMonthElement()).toFormatter();
      }
      return ymd;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.ISODateTimeFormat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */