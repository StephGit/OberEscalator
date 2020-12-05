// Generated by Dagger (https://google.github.io/dagger).
package oberescalator.di;

import android.app.Application;
import dagger.internal.Preconditions;
import oberescalator.OberEscalatorActivity;

public final class DaggerAppComponent implements AppComponent {
  private DaggerAppComponent(Builder builder) {}

  public static AppComponent.Builder builder() {
    return new Builder();
  }

  @Override
  public void inject(OberEscalatorActivity oberEscalatorActivity) {}

  private static final class Builder implements AppComponent.Builder {
    private Application application;

    @Override
    public AppComponent build() {
      Preconditions.checkBuilderRequirement(application, Application.class);
      return new DaggerAppComponent(this);
    }

    @Override
    public Builder application(Application application) {
      this.application = Preconditions.checkNotNull(application);
      return this;
    }
  }
}
