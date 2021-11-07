package com.covelopfit.autotrading.repository;
import com.covelopfit.autotrading.domain.Member;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class MemberRepository {

    private Member member;

    public void setKeyByMember(Member member) throws IOException {
        String keyFilePath = "/Users/taewoo/Park/" + member.getName() + "KeyFile.txt";
        List<String> lines = Files.readAllLines(Paths.get(keyFilePath));
        member.getKey().setAccessKey(lines.get(0));
        member.getKey().setSecretKey(lines.get(1));
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
