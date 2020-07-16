package dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Account {

    private final String owner;
    private final String number;
    private final Balance balance;
}
