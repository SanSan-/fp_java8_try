package dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NodeTree {

    private final int value;
    private final List<NodeTree> next;
}
