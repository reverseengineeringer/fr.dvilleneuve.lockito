package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>>
  extends MapperConfig<T>
  implements Serializable
{
  private static final int DEFAULT_MAPPER_FEATURES = collectFeatureDefaults(MapperFeature.class);
  private static final long serialVersionUID = 6062961959359172474L;
  protected final ContextAttributes _attributes;
  protected final Map<ClassKey, Class<?>> _mixInAnnotations;
  protected final String _rootName;
  protected final SubtypeResolver _subtypeResolver;
  protected final Class<?> _view;
  
  protected MapperConfigBase(BaseSettings paramBaseSettings, SubtypeResolver paramSubtypeResolver, Map<ClassKey, Class<?>> paramMap)
  {
    super(paramBaseSettings, DEFAULT_MAPPER_FEATURES);
    _mixInAnnotations = paramMap;
    _subtypeResolver = paramSubtypeResolver;
    _rootName = null;
    _view = null;
    _attributes = ContextAttributes.getEmpty();
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase)
  {
    super(paramMapperConfigBase);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = _subtypeResolver;
    _rootName = _rootName;
    _view = _view;
    _attributes = _attributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, int paramInt)
  {
    super(_base, paramInt);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = _subtypeResolver;
    _rootName = _rootName;
    _view = _view;
    _attributes = _attributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, BaseSettings paramBaseSettings)
  {
    super(paramBaseSettings, _mapperFeatures);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = _subtypeResolver;
    _rootName = _rootName;
    _view = _view;
    _attributes = _attributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, ContextAttributes paramContextAttributes)
  {
    super(paramMapperConfigBase);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = _subtypeResolver;
    _rootName = _rootName;
    _view = _view;
    _attributes = paramContextAttributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, SubtypeResolver paramSubtypeResolver)
  {
    super(paramMapperConfigBase);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = paramSubtypeResolver;
    _rootName = _rootName;
    _view = _view;
    _attributes = _attributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, Class<?> paramClass)
  {
    super(paramMapperConfigBase);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = _subtypeResolver;
    _rootName = _rootName;
    _view = paramClass;
    _attributes = _attributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, String paramString)
  {
    super(paramMapperConfigBase);
    _mixInAnnotations = _mixInAnnotations;
    _subtypeResolver = _subtypeResolver;
    _rootName = paramString;
    _view = _view;
    _attributes = _attributes;
  }
  
  protected MapperConfigBase(MapperConfigBase<CFG, T> paramMapperConfigBase, Map<ClassKey, Class<?>> paramMap)
  {
    super(paramMapperConfigBase);
    _mixInAnnotations = paramMap;
    _subtypeResolver = _subtypeResolver;
    _rootName = _rootName;
    _view = _view;
    _attributes = _attributes;
  }
  
  public final Class<?> findMixInClassFor(Class<?> paramClass)
  {
    if (_mixInAnnotations == null) {
      return null;
    }
    return (Class)_mixInAnnotations.get(new ClassKey(paramClass));
  }
  
  public final Class<?> getActiveView()
  {
    return _view;
  }
  
  public final ContextAttributes getAttributes()
  {
    return _attributes;
  }
  
  public final String getRootName()
  {
    return _rootName;
  }
  
  public final SubtypeResolver getSubtypeResolver()
  {
    return _subtypeResolver;
  }
  
  public final int mixInCount()
  {
    if (_mixInAnnotations == null) {
      return 0;
    }
    return _mixInAnnotations.size();
  }
  
  public abstract T with(Base64Variant paramBase64Variant);
  
  public abstract T with(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract T with(PropertyNamingStrategy paramPropertyNamingStrategy);
  
  public abstract T with(ContextAttributes paramContextAttributes);
  
  public abstract T with(HandlerInstantiator paramHandlerInstantiator);
  
  public abstract T with(ClassIntrospector paramClassIntrospector);
  
  public abstract T with(VisibilityChecker<?> paramVisibilityChecker);
  
  public abstract T with(SubtypeResolver paramSubtypeResolver);
  
  public abstract T with(TypeResolverBuilder<?> paramTypeResolverBuilder);
  
  public abstract T with(TypeFactory paramTypeFactory);
  
  public abstract T with(DateFormat paramDateFormat);
  
  public abstract T with(Locale paramLocale);
  
  public abstract T with(TimeZone paramTimeZone);
  
  public abstract T withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  public T withAttribute(Object paramObject1, Object paramObject2)
  {
    return with(getAttributes().withSharedAttribute(paramObject1, paramObject2));
  }
  
  public T withAttributes(Map<Object, Object> paramMap)
  {
    return with(getAttributes().withSharedAttributes(paramMap));
  }
  
  public abstract T withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract T withRootName(String paramString);
  
  public abstract T withView(Class<?> paramClass);
  
  public abstract T withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility);
  
  public T withoutAttribute(Object paramObject)
  {
    return with(getAttributes().withoutSharedAttribute(paramObject));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.cfg.MapperConfigBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */