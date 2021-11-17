package co.incubyte;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import net.bytebuddy.asm.Advice.Local;
import org.junit.Test;

public class ClockShould {
@Test public void
  return_todays_date_in_dd_MM_yyyy_format(){
  Clock clock=new TestableClock();
  String date=clock.todayAsString();
  assertThat(date,is("24/04/2020"));
}
private class TestableClock extends Clock{

  @Override
  protected LocalDate today() {
    return LocalDate.of(2020,4,24);
  }
}
}
