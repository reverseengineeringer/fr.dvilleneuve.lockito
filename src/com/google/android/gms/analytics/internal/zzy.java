package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzre;

public final class zzy
{
  public static zza<Boolean> A = zza.zzh("analytics.test.disable_receiver", false);
  public static zza<Long> B = zza.zza("analytics.service_client.idle_disconnect_millis", 10000L, 10000L);
  public static zza<Long> C = zza.zzb("analytics.service_client.connect_timeout_millis", 5000L);
  public static zza<Long> D = zza.zzb("analytics.service_client.second_connect_delay_millis", 5000L);
  public static zza<Long> E = zza.zzb("analytics.service_client.unexpected_reconnect_millis", 60000L);
  public static zza<Long> F = zza.zzb("analytics.service_client.reconnect_throttle_millis", 1800000L);
  public static zza<Long> G = zza.zzb("analytics.monitoring.sample_period_millis", 86400000L);
  public static zza<Long> H = zza.zzb("analytics.initialization_warning_threshold", 5000L);
  public static zza<Long> a;
  public static zza<Long> b;
  public static zza<Long> c;
  public static zza<Integer> d;
  public static zza<Integer> e;
  public static zza<String> f;
  public static zza<String> g;
  public static zza<String> h;
  public static zza<String> i;
  public static zza<Integer> j;
  public static zza<String> k;
  public static zza<String> l;
  public static zza<Integer> m;
  public static zza<Integer> n;
  public static zza<Integer> o;
  public static zza<Integer> p;
  public static zza<String> q;
  public static zza<Integer> r;
  public static zza<Long> s;
  public static zza<Integer> t;
  public static zza<Integer> u;
  public static zza<Long> v;
  public static zza<String> w;
  public static zza<Integer> z;
  public static zza<Boolean> zzczp = zza.zzh("analytics.service_enabled", false);
  public static zza<Boolean> zzczq = zza.zzh("analytics.service_client_enabled", true);
  public static zza<String> zzczr = zza.zze("analytics.log_tag", "GAv4", "GAv4-SVC");
  public static zza<Long> zzczs = zza.zzb("analytics.max_tokens", 60L);
  public static zza<Float> zzczt = zza.zza("analytics.tokens_per_sec", 0.5F);
  public static zza<Integer> zzczu = zza.zza("analytics.max_stored_hits", 2000, 20000);
  public static zza<Integer> zzczv = zza.zzd("analytics.max_stored_hits_per_app", 2000);
  public static zza<Integer> zzczw = zza.zzd("analytics.max_stored_properties_per_app", 100);
  public static zza<Long> zzczx = zza.zza("analytics.local_dispatch_millis", 1800000L, 120000L);
  public static zza<Long> zzczy = zza.zza("analytics.initial_local_dispatch_millis", 5000L, 5000L);
  public static zza<Long> zzczz = zza.zzb("analytics.min_local_dispatch_millis", 120000L);
  
  static
  {
    a = zza.zzb("analytics.max_local_dispatch_millis", 7200000L);
    b = zza.zzb("analytics.dispatch_alarm_millis", 7200000L);
    c = zza.zzb("analytics.max_dispatch_alarm_millis", 32400000L);
    d = zza.zzd("analytics.max_hits_per_dispatch", 20);
    e = zza.zzd("analytics.max_hits_per_batch", 20);
    f = zza.zzr("analytics.insecure_host", "http://www.google-analytics.com");
    g = zza.zzr("analytics.secure_host", "https://ssl.google-analytics.com");
    h = zza.zzr("analytics.simple_endpoint", "/collect");
    i = zza.zzr("analytics.batching_endpoint", "/batch");
    j = zza.zzd("analytics.max_get_length", 2036);
    k = zza.zze("analytics.batching_strategy.k", zzm.zzcyv.name(), zzm.zzcyv.name());
    l = zza.zzr("analytics.compression_strategy.k", zzo.zzczc.name());
    m = zza.zzd("analytics.max_hits_per_request.k", 20);
    n = zza.zzd("analytics.max_hit_length.k", 8192);
    o = zza.zzd("analytics.max_post_length.k", 8192);
    p = zza.zzd("analytics.max_batch_post_length", 8192);
    q = zza.zzr("analytics.fallback_responses.k", "404,502");
    r = zza.zzd("analytics.batch_retry_interval.seconds.k", 3600);
    s = zza.zzb("analytics.service_monitor_interval", 86400000L);
    t = zza.zzd("analytics.http_connection.connect_timeout_millis", 60000);
    u = zza.zzd("analytics.http_connection.read_timeout_millis", 61000);
    v = zza.zzb("analytics.campaigns.time_limit", 86400000L);
    w = zza.zzr("analytics.first_party_experiment_id", "");
    z = zza.zzd("analytics.first_party_experiment_variant", 0);
  }
  
  public static final class zza<V>
  {
    private final V I;
    private final zzre<V> J;
    
    private zza(zzre<V> paramzzre, V paramV)
    {
      zzab.zzaa(paramzzre);
      J = paramzzre;
      I = paramV;
    }
    
    static zza<Float> zza(String paramString, float paramFloat)
    {
      return zza(paramString, paramFloat, paramFloat);
    }
    
    static zza<Float> zza(String paramString, float paramFloat1, float paramFloat2)
    {
      return new zza(zzre.zza(paramString, Float.valueOf(paramFloat2)), Float.valueOf(paramFloat1));
    }
    
    static zza<Integer> zza(String paramString, int paramInt1, int paramInt2)
    {
      return new zza(zzre.zza(paramString, Integer.valueOf(paramInt2)), Integer.valueOf(paramInt1));
    }
    
    static zza<Long> zza(String paramString, long paramLong1, long paramLong2)
    {
      return new zza(zzre.zza(paramString, Long.valueOf(paramLong2)), Long.valueOf(paramLong1));
    }
    
    static zza<Boolean> zza(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      return new zza(zzre.zzm(paramString, paramBoolean2), Boolean.valueOf(paramBoolean1));
    }
    
    static zza<Long> zzb(String paramString, long paramLong)
    {
      return zza(paramString, paramLong, paramLong);
    }
    
    static zza<Integer> zzd(String paramString, int paramInt)
    {
      return zza(paramString, paramInt, paramInt);
    }
    
    static zza<String> zze(String paramString1, String paramString2, String paramString3)
    {
      return new zza(zzre.zzab(paramString1, paramString3), paramString2);
    }
    
    static zza<Boolean> zzh(String paramString, boolean paramBoolean)
    {
      return zza(paramString, paramBoolean, paramBoolean);
    }
    
    static zza<String> zzr(String paramString1, String paramString2)
    {
      return zze(paramString1, paramString2, paramString2);
    }
    
    public V get()
    {
      return (V)I;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */