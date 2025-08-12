

## **گزارش پروژه ماشین حساب با رویکرد BDD**

این گزارش به مستندسازی توسعه یک برنامه ماشین حساب می‌پردازد که از متدولوژی **توسعه مبتنی بر رفتار (BDD)** پیروی می‌کند. هدف اصلی، ایجاد یک سند بود که در آن الزامات برنامه، که به زبان ساده نوشته شده‌اند، به عنوان مجموعه تست خودکار نیز عمل کنند و از همسویی دقیق بین اهداف پیاده‌سازی نهایی اطمینان حاصل شود.

### **۱. گردش کار BDD: از رفتار تا کد**

ما رویکرد "از بیرون به درون" BDD را دنبال کردیم، با نگاه از منظر کاربر شروع کردیم و به جزئیات پیاده‌سازی رسیدیم.

  * **تعریف رفتار:** ابتدا ویژگی‌های ماشین حساب را در یک فایل `.feature` با فرمت قابل خواندن برای انسان و با استفاده از **سینتکس Gherkin** توصیف کردیم. این کار یک هدف واضح و بدون ابهام برای توسعه ما ایجاد کرد.
  * **پیاده‌سازی تعاریف گام (Step Definitions):** سپس "کد رابط" را نوشتیم تا هر گام به زبان ساده را به یک متد اجرایی جاوا ترجمه کند. در این مرحله، تست‌ها با شکست مواجه می‌شوند زیرا منطق برنامه اصلی هنوز وجود ندارد.
  * **نوشتن منطق اصلی:** در نهایت، با داشتن مجموعه‌ای واضح از تست‌های شکست‌خورده، منطق اصلی ماشین حساب را با تنها هدف موفقیت‌آمیز کردن این تست‌ها نوشتیم.

-----

### **۲. مرحله ۱: تعریف رفتارها با Gherkin**

پایه و اساس فرآیند BDD ما فایل `calculator.feature` است. این فایل تنها منبع معتبر برای آنچه که از ماشین حساب انتظار می‌رود، محسوب می‌شود.

  * **تست مبتنی بر داده با Scenario Outline:** برای جلوگیری از نوشتن سناریوهای تکراری برای هر عملیات ریاضی، از یک **Scenario Outline** استفاده کردیم. این ویژگی، تست‌های ما را مبتنی بر داده می‌کند و به ما اجازه می‌دهد یک ساختار تست واحد را تعریف کرده و چندین ترکیب از ورودی‌ها و نتایج مورد انتظار را از یک جدول Examples به آن بدهیم. این کار، خواندن و گسترش مجموعه تست را با موارد جدید آسان می‌کند.
  * **جدا کردن موارد خاص (Edge Cases):** ما یک Scenario Outline جداگانه به طور خاص برای مورد خاص و حساس تقسیم بر صفر ایجاد کردیم. این کار به وضوح رفتار مورد انتظار در مدیریت خطا را مستند می‌کند و آن را از محاسبات "مسیر موفقیت‌آمیز" جدا می‌سازد.

**Gherkin**

```gherkin
Feature: Calculator Operations

  Scenario Outline: Perform a mathematical calculation
    Given I have entered <operand1> into the calculator
    And I have entered <operand2> into the calculator
    When I press the <operator> button
    Then the result should be <result> on the screen

    Examples:
      | operand1 | operator | operand2 | result |
      | 5.0      | +        | 3.0      | 8.0    |
      | 6.0      | /        | 3.0      | 2.0    |

  Scenario Outline: Attempting to divide by zero with various numbers
    Given I have entered <dividend> into the calculator
    And I have entered 0.0 into the calculator
    When I press the / button
    Then an error message "Cannot divide by zero" should be displayed

    Examples:
      | dividend |
      | 10.0     |
```

-----

### **۳. مرحله ۲: پیاده‌سازی تعاریف گام ("کد رابط")**

کلاس `CalculatorStepDefs.java` به عنوان پل بین فایل `.feature` به زبان ساده و کد برنامه جاوا ما عمل می‌کند.

  * **جداسازی تست با `Before@`:** ما از یک متد با حاشیه‌نویسی `@Before` استفاده می‌کنیم تا قبل از اجرای هر سناریو، یک نمونه جدید از ماشین حساب ایجاد کرده و تمام متغیرهای حالت را بازنشانی کنیم. این کار برای اطمینان از مستقل بودن تست‌ها و تأثیر نگذاشتن آن‌ها بر یکدیگر، مهم است.
  * **مدیریت تمام نتایج:** گام `@When` که محاسبه را اجرا می‌کند، در یک بلوک `try...catch` قرار داده شده است. این کار به یک تعریف گام واحد اجازه می‌دهد تا هم سناریوهایی را که یک نتیجه معتبر تولید می‌کنند و هم سناریوهایی را که انتظار می‌رود یک استثنا ایجاد کنند، مدیریت کند.
  * **اعتبار سنجی‌های خاص با `@Then`:** ما دو متد مجزای `@Then` برای مدیریت دو نوع نتیجه تعریف‌شده در فایل ویژگی خود داریم. یکی از آن‌ها از `Assert.assertEquals` برای بررسی یک نتیجه عددی صحیح استفاده می‌کند، در حالی که دیگری یک پیام استثنای خاص را بررسی می‌کند و از این طریق اطمینان حاصل می‌کند که مدیریت خطای ما نیز به درستی تست می‌شود.

**Java**

```java
public class CalculatorStepDefs {
    private Calculator calculator;
    private Double result;
    private Exception thrownException;

    @Before
    public void setup() {
        calculator = new Calculator();
        //... reset variables
    }

    // ... @Given implementation

    @When("^I press the (.+) button$")
    public void i_press_the_button(String operator) {
        try {
            result = calculator.calculate(operand1, operand2, operator);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the result should be {double} on the screen")
    public void the_result_should_be_on_the_screen(Double expectedResult) {
        Assert.assertEquals(expectedResult, result);
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        Assert.assertNotNull("Expected an exception to be thrown", thrownException);
        Assert.assertEquals(expectedMessage, thrownException.getMessage());
    }
}
```

-----

### **۴. مرحله ۳: پیاده‌سازی منطق تجاری**

کلاس `Calculator.java` حاوی منطق اصلی برنامه است. این کد پس از تعریف رفتار و کد رابط نوشته شده است و از اصل **TDD** (توسعه مبتنی بر تست) پیروی می‌کند که در آن حداقل مقدار کد مورد نیاز برای موفقیت‌آمیز شدن تست‌ها نوشته می‌شود.

متد `calculate` برای وضوح و کارایی از یک عبارت `switch` استفاده می‌کند. این منطق مستقیماً الزامات موجود در تست‌های ما را پیاده‌سازی می‌کند، از جمله بررسی خاص برای جلوگیری از تقسیم بر صفر با ایجاد یک `IllegalArgumentException` با همان پیامی که در سناریوی Gherkin ما تعریف شده است.

**Java**

```java
public class Calculator {
    public Double calculate(Double operand1, Double operand2, String operator) {
        // ... input validation ...

        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/":
                if (operand2 == 0.0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
```

-----

### **۵. اجرای تست‌های رفتاری**

کلاس `RunnerTest.java` نقطه شروع برای اجرای کل مجموعه تست‌های رفتاری ما است. این کلاس از **JUnit** به عنوان اجراکننده استفاده می‌کند اما اجرای واقعی تست را **Cucumber** انجام می‌دهد. annotation `@CucumberOptions` به Cucumber می‌گوید که فایل‌های `.feature` (مشخصات) و کد رابط (تعاریف گام) را از کجا پیدا کند و کل چرخه BDD را به هم پیوند می‌دهد.

**Java**

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "calculator"
)
public class RunnerTest {
    // This class remains empty
}
```

هنگامی که این کلاس اجرا می‌شود، هر سناریو را اجرا کرده و یک گزارش جامع در مورد اینکه آیا وضعیت فعلی برنامه به درستی با رفتار مورد نیاز آن مطابقت دارد، ارائه می‌دهد.

<img width="1920" height="1004" alt="Screenshot (2583)" src="https://github.com/user-attachments/assets/e78311b9-67bf-4a46-a64d-f029be09ae16" />

