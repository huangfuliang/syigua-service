-- 创建提示词表
create table syg_prompt(
                           ID int(11) not null auto_increment,
                           PROMPT_CODE int(100) not null,
                           PROMPT varchar(10000) not null,
                           created_at datetime not null,
                           primary key(ID)
);
create index idx_syg_prompt_code on syg_prompt(PROMPT_CODE);
根据农历{userDate}信息，计算五行八字；直接输出结果，不需要过程，直接按照样例输出结果;输出样例：[{"八字":"天干", "年柱":"庚", "月柱":"丁", "日柱":"甲", "时柱":"戊"},{"八字":"地支", "年柱":"午", "月柱":"亥", "日柱":"辰", "时柱":"辰"}]

insert into syg_prompt(PROMPT_CODE, PROMPT, created_at) values(8001, '根据农历{userDate}信息，计算五行八字；直接输出结果，不需要过程，直接按照样例输出结果;输出样例：[{"八字":"天干", "年柱":"庚", "月柱":"丁", "日柱":"甲", "时柱":"戊"},{"八字":"地支", "年柱":"午", "月柱":"亥", "日柱":"辰", "时柱":"辰"}]', now());
