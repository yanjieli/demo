package anno;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Son extends Father {

    @Override
    public void speak() {
        log.info("Son is speaking some word!");
    }

}
